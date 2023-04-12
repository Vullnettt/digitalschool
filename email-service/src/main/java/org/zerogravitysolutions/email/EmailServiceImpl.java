package org.zerogravitysolutions.email;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.templateresolver.StringTemplateResolver;
import org.zerogravitysolutions.email.utils.EmailMapper;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

@Service
public class EmailServiceImpl implements EmailService {

    private final EmailRepository emailRepository;
    private final JavaMailSender javaMailSender;
    private final SpringTemplateEngine springTemplateEngine;
    private static final Logger LOGGER = LoggerFactory.getLogger(EmailServiceImpl.class);
    private final EmailMapper emailMapper;

    @Autowired
    public EmailServiceImpl(EmailRepository emailRepository, JavaMailSender javaMailSender, SpringTemplateEngine springTemplateEngine, EmailMapper emailMapper) {
        this.emailRepository = emailRepository;
        this.javaMailSender = javaMailSender;
        this.springTemplateEngine = springTemplateEngine;
        this.emailMapper = emailMapper;
    }

    @Override
    public ResponseEntity<EmailDto> save(EmailDto emailDto) {
        EmailEntity emailEntity = new EmailEntity();
        emailEntity.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        emailEntity.setCreatedBy(1L);
        emailMapper.mapDtoToEntity(emailDto, emailEntity);
        return ResponseEntity.ok().body(emailMapper.mapsEntityToDto(emailRepository.save(emailEntity), emailDto));
    }

    @Override
    public ResponseEntity<?> sendEmail(List<String> recipients,
                                       String subject,
                                       String body,
                                       List<File> attachments,
                                       String templateFile,
                                       Map<String, Object> templateData) {
        if(recipients == null || recipients.size() == 0){
            throw new IllegalArgumentException("Recipient must not be empty.");
        }

        if(subject == null) {
            throw new IllegalArgumentException("Subject mos not be null");
        }

        if(body == null && templateFile == null) {
            throw new IllegalArgumentException("Either body or templateContent must be provided.");
        }

        MimeMessagePreparator messagePreparator = mimeMessage -> {
            MimeMessageHelper messageHelper = new MimeMessageHelper(
                    mimeMessage, true, StandardCharsets.UTF_8.name());
            messageHelper.setTo(recipients.toArray(new String[0]));
            messageHelper.setSubject(subject);

            if(templateFile != null){
                String html = processTemplate(templateFile, templateData);
                messageHelper.setText(html, true);
            }else{
                messageHelper.setText(body);
            }

            if(attachments != null){
                for(File attachment : attachments){
                    messageHelper.addAttachment(attachment.getName(), attachment);
                }
            }
        };

        try{
            javaMailSender.send(messagePreparator);
            LOGGER.info("Email sent successfully to {}", recipients);
            return ResponseEntity.ok().body("Email sent successfully.");
        } catch (MailException e) {
            LOGGER.error("Failed to send email to {}", recipients);
            return ResponseEntity.badRequest().body("Failed to send email.");
        }
    }

    private String processTemplate(String templateFile, Map<String, Object> templateData) {
        StringTemplateResolver templateResolver = new StringTemplateResolver();
        springTemplateEngine.setTemplateResolver(templateResolver);

        Context context = new Context();
        context.setVariables(templateData);

        return springTemplateEngine.process(templateFile, context);
    }
}

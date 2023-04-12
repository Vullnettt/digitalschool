package org.zerogravitysolutions.email;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.apache.commons.validator.routines.EmailValidator;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.*;

@RestController
public class EmailController {

    private final EmailService emailService;
    private final ObjectMapper objectMapper;
    private static final Logger LOGGER = LoggerFactory.getLogger(EmailController.class);

    @Autowired
    public EmailController(EmailService emailService, ObjectMapper objectMapper) {
        this.emailService = emailService;
        this.objectMapper = objectMapper;
    }


    @PostMapping(path = "/send", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(
            description = "Send Email by providing a mandatory list of as recipients and a subject, optionals message body, attachments, template file, template data",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Send Email",
                            content = @Content(
                                    mediaType = "application/json",
                                    examples ={
                                            @ExampleObject(
                                                    value = "{\"code\" : 200, \"Status\" : \"Ok!\", \"Message\" : \"Email has been sent successfully!\"}"
                                            )
                            }
                            )
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Bad Request",
                            content = @Content(
                                    mediaType = "application/json",
                                    examples ={
                                            @ExampleObject(
                                                    value = "{\"code\" : 400, \"Status\" : \"Bad Request!\", \"Message\" : \"Error while sending Email!\"}"
                                            )
                                    }
                            )
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Internal Server Error",
                            content = @Content(
                                    mediaType = "application/json",
                                    examples ={
                                            @ExampleObject(
                                                    value = "{\"code\" : 500, \"Status\" : \"Internal Server Error!\", \"Message\" : \"Internal Server Error!\"}"
                                            )
                                    }
                            )
                    )
            }
    )
    public ResponseEntity<?> send(@RequestPart(name = "recipients", required = true) String recipients,
                                  @RequestPart(name = "subject") String subject,
                                  @RequestPart(name = "body", required = false) String body,
                                  @RequestPart(name = "attachments", required = false) MultipartFile[] attachments,
                                  @RequestPart(name = "templateFile", required = false) MultipartFile templateFile,
                                  @RequestPart(name = "templateData", required = false) String templateData){

        EmailDto email = new EmailDto();
        try{
            LOGGER.info("Sending email with {} recipients, subject: {}", recipients, subject);

            List<String> recipientsArray = objectMapper.readValue(recipients, new TypeReference<List<String>>() {});
            List<String> validRecipients = validateRecipients(recipientsArray);

            if(validRecipients.isEmpty()){
                LOGGER.error("No valid recipients found.");
                return ResponseEntity.badRequest().body("No valid recipients found.");
            }

            if ((templateFile != null && templateData == null) || (templateFile == null && templateData != null)) {
                LOGGER.error("Both template and templateData mut be provided together.");
                return ResponseEntity.badRequest().body("Both template and templateData mut be provided together.");
            }

            Map<String, Object> templateDataObject = new HashMap<>();
            if(templateData != null){
                email.setTemplateData(templateData);
                email.setTemplateFile(templateFile.getOriginalFilename());
                templateDataObject = objectMapper.readValue(templateData, new TypeReference<Map<String, Object>>() {});
            }

            String templateContent = proccessTemplateFile(templateFile);
            List<File> attachmentsFile = proccessAttachments(attachments);

            email.setBody(body);
            email.setRecipients(recipients);
            email.setSubject(subject);
            emailService.save(email);
            emailService.sendEmail(validRecipients, subject, body, attachmentsFile, templateContent, templateDataObject);
            return ResponseEntity.ok().body("Email has been sent successfully!");
        }catch (Exception e){
            LOGGER.error("Error while sending email.");
            return ResponseEntity.badRequest().body("Error while sending email!");
        }
    }

    private List<String> validateRecipients(List<String> recipients) {
        EmailValidator emailValidator = EmailValidator.getInstance();
        List<String> validRecipients = new ArrayList<>();
        for(String recipient : recipients){
            if(emailValidator.isValid(recipient)){
                validRecipients.add(recipient);
            }
        }
        return validRecipients;
    }

    private String proccessTemplateFile(MultipartFile templateFile) throws IOException {
        if(templateFile == null){
            return null;
        }
        return new String(templateFile.getBytes(), StandardCharsets.UTF_8);
    }

    private List<File> proccessAttachments(MultipartFile[] attachments) throws IOException {
        if(attachments == null){
            return Collections.emptyList();
        }

        List<File> attachmentsFile = new ArrayList<>();
        for(MultipartFile attachment : attachments){
            File templateFile = File.createTempFile("attachment-" ,"-" + attachment.getOriginalFilename());
            attachment.transferTo(templateFile);
            attachmentsFile.add(templateFile);
        }

        return attachmentsFile;
    }
}

package org.zerogravitysolutions.email;

import org.springframework.http.ResponseEntity;

import java.io.File;
import java.util.List;
import java.util.Map;

public interface EmailService {
    ResponseEntity<EmailEntity> save(EmailEntity email);

    ResponseEntity<?> sendEmail(List<String> validRecipients, String subject, String body, List<File> attachmentsFile, String templateContent, Map<String, Object> templateDataObject);
}

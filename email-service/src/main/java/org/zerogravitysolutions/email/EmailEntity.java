package org.zerogravitysolutions.email;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import org.zerogravitysolutions.commons.BaseEntity;

@Entity
@Table(name = "emails")
public class EmailEntity extends BaseEntity {

    private String recipients;
    private String subject;
    private String body;
    private String templateFile;
    private String templateData;

    public String getRecipients() {
        return recipients;
    }

    public void setRecipients(String recipients) {
        this.recipients = recipients;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getTemplateFile() {
        return templateFile;
    }

    public void setTemplateFile(String templateFile) {
        this.templateFile = templateFile;
    }

    public String getTemplateData() {
        return templateData;
    }

    public void setTemplateData(String templateData) {
        this.templateData = templateData;
    }
}

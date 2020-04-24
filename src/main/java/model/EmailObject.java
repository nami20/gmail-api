package model;

import com.google.api.services.gmail.Gmail;
import java.io.File;

public class EmailObject {
    public Gmail service;
    public String recipientAddress;
    public String fromAddress;
    public String subject;
    public String body;
    public File file;

    public EmailObject(Gmail service, String recipientAddress,
                       String fromAddress, String subject, String body, File file) {
        this.service = service;
        this.recipientAddress = recipientAddress;
        this.fromAddress = fromAddress;
        this.subject = subject;
        this.body = body;
        this.file = file;
    }

    public EmailObject(Gmail service, String recipientAddress,
                       String fromAddress, String subject, String body) {
        this.service = service;
        this.recipientAddress = recipientAddress;
        this.fromAddress = fromAddress;
        this.subject = subject;
        this.body = body;
    }

    public Gmail getService() {
        return service;
    }

    public String getRecipientAddress() {
        return recipientAddress;
    }

    public String getFromAddress() {
        return fromAddress;
    }

    public String getSubject() {
        return subject;
    }

    public String getBody() {
        return body;
    }

    public File getFile() {
        return file;
    }

    public void setService(Gmail service) {
        this.service = service;
    }

    public void setRecipientAddress(String recipientAddress) {
       this.recipientAddress = recipientAddress;
    }

    public void setFromAddress(String fromAddress) {
        this.fromAddress = fromAddress;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public void setFile(File file) {
        this.file = file;
    }
}

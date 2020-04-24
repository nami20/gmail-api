package model;

import com.google.api.services.gmail.Gmail;
import java.io.File;
import java.util.Optional;

public class EmailObject {
    public Gmail service;
    public String recipientAddress;
    public String fromAddress;
    public String subject;
    public String body;
    public Optional<File> file;

    public EmailObject(Gmail service, String recipientAddress,
                       String fromAddress, String subject, String body, Optional<File> file) {
        this.service = service;
        this.recipientAddress = recipientAddress;
        this.fromAddress = fromAddress;
        this.subject = subject;
        this.body = body;
        if(file != null) {
            this.file = file;
        }
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

    public Optional<File> getFile() {
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

    public void setFile(Optional<File> file) {
        this.file = file;
    }
}

package service;

import com.google.api.services.gmail.Gmail;

import javax.mail.MessagingException;
import java.io.IOException;
import java.io.File;

public interface GoogleService {

    boolean sendMessage(Gmail service, String recipientAddress, String fromAddress, String subject, String body) throws MessagingException, IOException;

    boolean sendMessageWithAttachment(Gmail service, String recipientAddress, String fromAddress, String subject, String body, File file) throws MessagingException, IOException;
}
package service;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.Base64;
import com.google.api.services.gmail.Gmail;
import com.google.api.services.gmail.model.Message;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Multipart;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.Properties;
import java.util.Optional;

import model.EmailObject;

public final class GoogleServiceImpl implements GoogleService {

    @Override
    public boolean sendMessage(EmailObject emailObject) throws MessagingException,
            IOException {
        Gmail service = emailObject.getService();
        String recipientAddress = emailObject.getRecipientAddress();
        String fromAddress = emailObject.getFromAddress();
        String subject = emailObject.getSubject();
        String body = emailObject.getBody();
        Optional<File> file = emailObject.getFile();
        MimeMessage emailContent = null;
        emailContent = createEmail(recipientAddress, fromAddress, subject, body, file);
        Message message = createMessageWithEmail(emailContent);

        return service.users()
                .messages()
                .send(fromAddress, message)
                .execute()
                .getLabelIds().contains("SENT");
    }

    private MimeMessage createEmail(String to, String from, String subject, String bodyText, Optional<File> file) throws MessagingException {
        MimeMessage email = new MimeMessage(Session.getDefaultInstance(new Properties(), null));
        email.setFrom(new InternetAddress(from));
        email.addRecipient(javax.mail.Message.RecipientType.TO, new InternetAddress(to));
        email.setSubject(subject);

        if(file == null) {
            email.setText(bodyText);
        } else {
            File fileAttachment = file.get();
            MimeBodyPart mimeBodyPart = new MimeBodyPart();
            mimeBodyPart.setContent(bodyText, "text/plain");

            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(mimeBodyPart);

            mimeBodyPart = new MimeBodyPart();
            DataSource source = new FileDataSource(fileAttachment);

            mimeBodyPart.setDataHandler(new DataHandler(source));
            mimeBodyPart.setFileName(fileAttachment.getName());

            multipart.addBodyPart(mimeBodyPart);
            email.setContent(multipart);
        }

        return email;

    }

    private Message createMessageWithEmail(MimeMessage emailContent) throws MessagingException, IOException {
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        emailContent.writeTo(buffer);

        return new Message()
                .setRaw(Base64.encodeBase64URLSafeString(buffer.toByteArray()));
    }
}
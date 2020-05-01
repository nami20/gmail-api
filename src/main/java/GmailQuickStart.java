import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.gmail.Gmail;
import com.google.api.services.gmail.GmailScopes;
import com.google.api.services.gmail.model.Label;
import com.google.api.services.gmail.model.ListLabelsResponse;
import com.google.api.services.gmail.model.Message;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.FileNotFoundException;
import javax.mail.MessagingException;
import java.lang.NullPointerException;
import java.io.IOException;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import javax.mail.internet.MimeMessage;
import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.List;

import service.GoogleService;
import service.GoogleServiceImpl;
import model.EmailObject;
import java.util.Optional;


public class GmailQuickStart {

    private static final String APPLICATION_NAME = "fight club";
    private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();

    private static Credential getCredentials(final NetHttpTransport HTTP_TRANSPORT) throws IOException {
        return new GoogleCredential.Builder()
                .setTransport(HTTP_TRANSPORT)
                .setJsonFactory(JSON_FACTORY)
                .setClientSecrets(client_id, client_secret)
                .build()
                .setAccessToken(access_token)
                .setRefreshToken(refresh_token);
    }


    public static void main(String... args) throws IOException, GeneralSecurityException {
        try {
            GoogleService googleService = new GoogleServiceImpl();
            final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
            Gmail service = new Gmail.Builder(HTTP_TRANSPORT, JSON_FACTORY,
                    getCredentials(HTTP_TRANSPORT))
                    .setApplicationName(APPLICATION_NAME)
                    .build();
            String fileName = "static/images/download.png";
            ClassLoader classLoader = new GmailQuickStart().getClass().getClassLoader();
            File file = new File(classLoader.getResource(fileName).getFile());
            EmailObject emailObjectWithAttacthment = new EmailObject(service, "tnotna01@gmail.com", "teenu.nota@technogramsolutions.com", "Subject", "body text", Optional.ofNullable(file));
            EmailObject emailObject = new EmailObject(service, "tnotna01@gmail.com", "teenu.nota@technogramsolutions.com", "Subject", "body text", null);
            googleService.sendMessage(emailObject);
            googleService.sendMessage(emailObjectWithAttacthment);
        } catch (NullPointerException | IOException | MessagingException e) {
            e.printStackTrace();
        }
    }
}
package service;

import com.google.api.services.gmail.Gmail;

import javax.mail.MessagingException;
import java.io.IOException;
import java.io.File;

import model.EmailObject;

public interface GoogleService {

    boolean sendMessage(EmailObject emailObject) throws MessagingException, IOException;
}
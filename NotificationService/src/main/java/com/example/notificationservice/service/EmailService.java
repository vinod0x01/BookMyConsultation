package com.example.notificationservice.service;

import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import java.io.IOException;

@Component
public class EmailService {

    public EmailService() {}



    @Autowired
    private SesEmailVerificationService verifyEmail;

    public void sendWelcomeEmail(String user, String emailId) throws TemplateException, MessagingException, IOException {
        verifyEmail.sendVerificationEmail(user, emailId);
    }

}

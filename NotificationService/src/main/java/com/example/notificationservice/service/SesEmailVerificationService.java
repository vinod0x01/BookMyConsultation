package com.example.notificationservice.service;

import com.example.notificationservice.entity.Appointment;
import com.example.notificationservice.entity.User;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.ses.SesClient;

import javax.annotation.PostConstruct;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

@RequiredArgsConstructor
@Component
public class SesEmailVerificationService {

    private SesClient sesClient;
    private final FreeMarkerConfigurer configurer;

    @Value("${ses.accessKey}")
    private String accessKey;

    @Value("${ses.secretKey}")
    private String secretKey;

    @Value("${ses.fromEmail}")
    private String fromEmail;

    @PostConstruct
    public void init(){

        StaticCredentialsProvider staticCredentialsProvider = StaticCredentialsProvider
                .create(AwsBasicCredentials.create(accessKey,secretKey));

        sesClient = SesClient.builder()
                .credentialsProvider(staticCredentialsProvider)
                .region(Region.US_EAST_1)
                .build();
    }

    public void verifyEmail(String emailId){
        sesClient.verifyEmailAddress(req->req.emailAddress(emailId));
    }

    public void sendVerificationEmail(String user, String emailId) throws IOException, TemplateException, MessagingException {
        Map<String,Object> templateModel = new HashMap<>();
        templateModel.put("name",user);
        Template freeMarkerTemplate = configurer.getConfiguration().getTemplate("docwelcome.ftl");
        String htmlBody = FreeMarkerTemplateUtils.processTemplateIntoString(freeMarkerTemplate,templateModel);
        sendMail(emailId,"Welcome Email",htmlBody);
    }
    public void sendVerificationEmail(User user) throws IOException, TemplateException, MessagingException {
        Map<String,Object> templateModel = new HashMap<>();
        templateModel.put("user",user);
        Template freeMarkerTemplate = configurer.getConfiguration().getTemplate("userwelcome.ftl");
        String htmlBody = FreeMarkerTemplateUtils.processTemplateIntoString(freeMarkerTemplate,templateModel);
        sendMail(user.getEmailId(),"Welcome Email",htmlBody);
    }

    public void sendApprovalEmail(User user) throws IOException, TemplateException, MessagingException {
        Map<String,Object> templateModel = new HashMap<>();
        templateModel.put("user",user);
        Template freeMarkerTemplate = configurer.getConfiguration().getTemplate("docapproval.ftl");
        String htmlBody = FreeMarkerTemplateUtils.processTemplateIntoString(freeMarkerTemplate,templateModel);
        sendMail(user.getEmailId(),"Registration Approved!",htmlBody);
    }

    public void sendRejectionEmail(User user) throws IOException, TemplateException, MessagingException {
        Map<String,Object> templateModel = new HashMap<>();
        templateModel.put("user",user);
        Template freeMarkerTemplate = configurer.getConfiguration().getTemplate("docrejection.ftl");
        String htmlBody = FreeMarkerTemplateUtils.processTemplateIntoString(freeMarkerTemplate,templateModel);
        sendMail(user.getEmailId(),"Registration Rejected!",htmlBody);
    }

    public void sendAppointmentConfirmationEmail(Appointment appointment) throws IOException, TemplateException, MessagingException {
        Map<String,Object> templateModel = new HashMap<>();
        templateModel.put("appointment",appointment);
        Template freeMarkerTemplate = configurer.getConfiguration().getTemplate("appconfirmation.ftl");
        String htmlBody = FreeMarkerTemplateUtils.processTemplateIntoString(freeMarkerTemplate,templateModel);
        sendMail(appointment.getEmailId(),"Appointment Confirmed!",htmlBody);
    }

    public void sendPrescription(String apptId, String mailId, String medicineList) throws IOException, TemplateException, MessagingException {
        Map<String,Object> templateModel = new HashMap<>();
        templateModel.put("appointmentId",apptId);
        templateModel.put("medicineList",medicineList);
        Template freeMarkerTemplate = configurer.getConfiguration().getTemplate("prescription.ftl");
        String htmlBody = FreeMarkerTemplateUtils.processTemplateIntoString(freeMarkerTemplate,templateModel);
        sendMail(mailId,"Prescription Attached!",htmlBody);
    }

    private void sendMail(String toEmail, String subject, String body) throws MessagingException {
        Properties props = System.getProperties();
        props.put("mail.transport.protocol","smtp");
        props.put("mail.smtp.port",587);
        props.put("mail.smtp.starttls.enable","true");
        props.put("mail.smtp.auth","true");
        javax.mail.Session session = javax.mail.Session.getDefaultInstance(props);
        MimeMessage msg = new MimeMessage(session);
        msg.setFrom(fromEmail);
        msg.setRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));
        msg.setSubject(subject);
        msg.setContent(body,"text/html");
        Transport transport = session.getTransport();
        try {
            transport.connect("email-smtp.us-east-1.amazonaws.com", accessKey, secretKey);
            transport.sendMessage(msg, msg.getAllRecipients());
        }catch(Exception e){
            System.out.println(e.getMessage());
        }finally {
            transport.close();
        }
    }

}

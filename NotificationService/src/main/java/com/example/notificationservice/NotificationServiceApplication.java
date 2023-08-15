package com.example.notificationservice;

import com.example.notificationservice.entity.Appointment;
import com.example.notificationservice.entity.User;
import com.example.notificationservice.service.SesEmailVerificationService;
import freemarker.template.TemplateException;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.json.*;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import java.io.IOException;
import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;
import java.util.Set;

@SpringBootApplication
public class NotificationServiceApplication {

    public static void main(String[] args) {


        ApplicationContext applicationContext = SpringApplication.run(NotificationServiceApplication.class, args);

        Properties properties = new Properties();

        KafkaHost kafkaHost = applicationContext.getBean(KafkaHost.class);

        // String kafkaConnectionstring = System.getenv("KAFKA_HOST")+":"+System.getenv("KAFKA_HOST_PORT");


        properties.put("bootstrap.servers", kafkaHost.getKafkaConnectionString());
        properties.put("group.id", "test");
        properties.put("enable.auto.commit", "true");
        properties.put("auto.commit.interval.ms", "1000");
        properties.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        properties.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");


        KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(properties);
        consumer.subscribe(Arrays.asList("new_doc","new_user", "doc_approval", "doc_rejection", "app_confirm", "prescription"));

        Set<String> subscribedTopics = consumer.subscription();
        subscribedTopics.stream().forEach(System.out::println);

        try {
            while (true) {
                ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(100));
                for (ConsumerRecord<String, String> record : records) {
                    System.out.println(record.value());

                    String topic = record.topic();
                    JSONObject obj = new JSONObject(record.value());

                    if (topic.equals("new_doc")) {
                        String name = obj.getString("firstName");
                        String emailId = obj.getString("emailId");
                        User user = new User(name, emailId,null);
                        SesEmailVerificationService emailService = applicationContext.getBean(SesEmailVerificationService.class);
                        emailService.sendVerificationEmail(name,emailId);
                    }else if(topic.equals("doc_approval")){
                        String name = obj.getString("firstName");
                        String emailId = obj.getString("emailId");
                        User user = new User(name, emailId,null);
                        SesEmailVerificationService emailService = applicationContext.getBean(SesEmailVerificationService.class);
                        emailService.sendApprovalEmail(user);
                    }else if(topic.equals("doc_rejection")){
                        String name = obj.getString("firstName");
                        String emailId = obj.getString("emailId");
                        String comments = obj.getString("approverComments");
                        User user = new User(name, emailId,comments);
                        SesEmailVerificationService emailService = applicationContext.getBean(SesEmailVerificationService.class);
                        emailService.sendRejectionEmail(user);
                    }
                    else if(topic.equals("app_confirm")){
                        String docName = obj.getString("doctor_name");
                        String date = obj.getString("appointment_date");
                        String timeslot = obj.getString("timeslot");
                        String status = obj.getString("status");
                        String emailId = obj.getString("user_email_id");
                        Appointment appointment = new Appointment(docName,date,timeslot,status,emailId);
                        SesEmailVerificationService emailService = applicationContext.getBean(SesEmailVerificationService.class);
                        emailService.sendAppointmentConfirmationEmail(appointment);
                    } else if(topic.equals("new_user")){
                        String name = obj.getString("firstName");
                        String emailId = obj.getString("emailId");
                        User user = new User(name, emailId,null);
                        SesEmailVerificationService emailService = applicationContext.getBean(SesEmailVerificationService.class);
                        emailService.sendVerificationEmail(user);
                    }else if(topic.equals("prescription")){
                        String appId = obj.getString("appointmentId");
                        String emailId = obj.getString("emailId");
                        String medicineList = obj.getJSONArray("medicineList").toString();
                        SesEmailVerificationService emailService = applicationContext.getBean(SesEmailVerificationService.class);
                        emailService.sendPrescription(appId,emailId,medicineList);
                    }
                }

            }
        } catch (TemplateException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            consumer.close();
        }
    }

    @Component
    class KafkaHost {

        @Value("${kafka_host}")
        private String kafkaHostURL;

        @Value("${kafka_port}")
        private String kafkaHostPort;

        public String getKafkaConnectionString() {
            return kafkaHostURL+":"+kafkaHostPort;
        }
    }
}


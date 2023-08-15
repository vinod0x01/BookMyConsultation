package com.bookmyconsultation.doctorservice;

import com.amazonaws.services.s3.model.ObjectMetadata;
import com.bookmyconsultation.doctorservice.service.DoctorServiceImpl;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.MessagingException;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;
import java.util.Set;

@SpringBootApplication
@EnableEurekaClient
public class DoctorServiceApplication {
    @Bean
    ObjectMetadata objectMetadata() {
        return new ObjectMetadata();
    }

    public static void main(String[] args) {
        ApplicationContext applicationContext = SpringApplication.run(DoctorServiceApplication.class, args);

        Properties properties = new Properties();
        KafkaHost kafkaHost = applicationContext.getBean(KafkaHost.class);

        properties.put("bootstrap.servers", kafkaHost.getKafkaConnectionURL());
        properties.put("group.id", "test");
        properties.put("enable.auto.commit", "true");
        properties.put("auto.commit.interval.ms", "1000");
        properties.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        properties.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");


        KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(properties);
        consumer.subscribe(Arrays.asList("message"));

        Set<String> subscribedTopics = consumer.subscription();
        subscribedTopics.stream().forEach(System.out::println);

        try {
            while (true) {
                ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(100));
                for (ConsumerRecord<String, String> record : records) {
                    System.out.println(record.value());

                    String topic = record.topic();
                    JSONObject obj = new JSONObject(record.value());

                    DoctorServiceImpl doctorService = applicationContext.getBean(DoctorServiceImpl.class);

                    doctorService.updateRating(obj.getInt("rating"),obj.getString("doctorId"));

                }

            }
        } catch (MessagingException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            throw new RuntimeException(e);
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

        public String getKafkaConnectionURL() {
            return kafkaHostURL+":"+kafkaHostPort;
        }
    }
}

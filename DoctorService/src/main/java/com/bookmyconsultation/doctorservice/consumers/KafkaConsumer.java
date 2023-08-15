package com.bookmyconsultation.doctorservice.consumers;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.springframework.beans.factory.annotation.Value;

import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;
import java.util.Set;

public class KafkaConsumer {

    @Value("${kafka_host}")
    private static String kafkaHostURL;

    @Value("${kafka_port}")
    private static String kafkaHostPort;

	public static void main(String[] args) {

		
        Properties props = new Properties();

        props.put("bootstrap.servers", kafkaHostURL+":"+kafkaHostPort);

        props.setProperty("group.id", "sweethome");
        props.setProperty("enable.auto.commit", "true");
        props.setProperty("auto.commit.interval.ms", "1000");
        props.setProperty("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.setProperty("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");

        org.apache.kafka.clients.consumer.KafkaConsumer<String, String> consumer = new org.apache.kafka.clients.consumer.KafkaConsumer<String, String>(props);
        consumer.subscribe(Arrays.asList("message"));
        //Prints the topic subscription list
        Set<String> subscribedTopics = consumer.subscription();
        for(String topic : subscribedTopics) {
        	System.out.println(topic);
        }
        
        
        try {
        	while(true) {
        		ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(100));
        		for(ConsumerRecord<String, String> record : records) {
        			System.out.println(record.value());
        		}
        	}
        }finally {
			consumer.close();
		}

	}

}

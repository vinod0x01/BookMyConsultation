package com.bookmyconsultation.doctorservice.producers;

import java.io.IOException;

public interface KafkaMessageProducer {

    void publish(String topic, String key, String value) throws IOException;
}

package com.bookmyconsultation.appointmentservice.producers;

import java.io.IOException;

public interface KafkaMessageProducer {

    void publish(String topic, String key, String value) throws IOException;
}

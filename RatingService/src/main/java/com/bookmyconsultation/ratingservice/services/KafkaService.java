package com.bookmyconsultation.ratingservice.services;

import java.io.IOException;

public interface KafkaService {

    public void produceMessage(String topicName, String key, String value) throws IOException;

}

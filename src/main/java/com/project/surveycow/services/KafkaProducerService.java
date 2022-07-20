package com.project.surveycow.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducerService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private static final String TOPIC_NAME = "answer";

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    public void sendMessage(String message) {
        logger.info(String.format("Message sent -> %s", message));
        kafkaTemplate.send(TOPIC_NAME, message);
    }

}

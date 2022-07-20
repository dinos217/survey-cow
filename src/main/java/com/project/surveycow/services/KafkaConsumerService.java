package com.project.surveycow.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @KafkaListener(topics = "answer", groupId = "surveycow_loggers")
    public void consume(String message) {
        logger.info(String.format("Message received -> %s", message));
    }
}

package com.example.kafka_consumer.service;

import com.example.kafka_consumer.model.MessageDTO;

public interface EmailService {
    void sendEmail(MessageDTO messageDTO);
}

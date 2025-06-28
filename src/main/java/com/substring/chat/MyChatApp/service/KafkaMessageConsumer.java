package com.substring.chat.MyChatApp.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaMessageConsumer {

    private final MessageService messageService;

    public KafkaMessageConsumer(MessageService messageService) {
        this.messageService = messageService;
    }

    @KafkaListener(topics = "chat-messages", groupId = "chat-consumer")
    public void consume(String message) {
        messageService.saveMessage(message);
    }
}

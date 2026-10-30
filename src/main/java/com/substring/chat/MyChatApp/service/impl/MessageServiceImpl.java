package com.substring.chat.MyChatApp.service.impl;

import com.substring.chat.MyChatApp.entities.ChatMessage;
import com.substring.chat.MyChatApp.repositories.ChatMessageRepository;
import com.substring.chat.MyChatApp.service.MessageService;
import org.springframework.stereotype.Service;

@Service
public class MessageServiceImpl implements MessageService {

    private final ChatMessageRepository repository;

    public MessageServiceImpl(ChatMessageRepository repository) {
        this.repository = repository;
    }

    @Override
    public void saveMessage(String content) {
        ChatMessage message = new ChatMessage();
        message.setContent(content);
        System.out.println("Message beign saved on database");
        repository.save(message);
        System.out.println("Message succesfully saved on database");
    }
}
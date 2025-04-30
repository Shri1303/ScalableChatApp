package com.substring.chat.MyChatApp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.substring.chat.MyChatApp.entities.ChatMessage;
import java.util.UUID;

public interface ChatMessageRepository extends JpaRepository<ChatMessage, UUID> {
    
}

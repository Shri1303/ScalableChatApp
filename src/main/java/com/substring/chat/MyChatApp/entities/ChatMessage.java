package com.substring.chat.MyChatApp.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ChatMessage {
    private String content;
    private String sender;
    private MessageType type;

    public String getSender() {
        return sender;
    }

    public enum MessageType {
        CHAT,
        JOIN,
        LEAVE
    }

    // Getters and setters
}

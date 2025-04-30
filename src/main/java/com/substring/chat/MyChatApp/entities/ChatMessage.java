package com.substring.chat.MyChatApp.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class ChatMessage {

    @Id
    @GeneratedValue
    private UUID id;

    private String content;
    private LocalDateTime sentAt = LocalDateTime.now();

    public void setContent(String content) {
        this.content=content;
    }

}

package com.substring.chat.MyChatApp.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

import com.substring.chat.MyChatApp.websocket.WebSocketRedisHandler;
// (add import)

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

    private final WebSocketRedisHandler webSocketRedisHandler;

    public WebSocketConfig(WebSocketRedisHandler webSocketRedisHandler) {
        this.webSocketRedisHandler = webSocketRedisHandler;
    }

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(webSocketRedisHandler, "/ws")
                .setAllowedOrigins("*");
    }
}

package com.substring.chat.MyChatApp.websocket;

import com.substring.chat.MyChatApp.service.RedisPubSubService;
import com.substring.chat.MyChatApp.service.WebSocketSessionManager;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.*;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@Component
public class WebSocketRedisHandler extends TextWebSocketHandler {

    private final RedisPubSubService redisPubSubService;
    private final WebSocketSessionManager sessionManager;

    public WebSocketRedisHandler(RedisPubSubService redisPubSubService, WebSocketSessionManager sessionManager) {
        this.redisPubSubService = redisPubSubService;
        this.sessionManager = sessionManager;

        // Subscribe to Redis on startup
        this.redisPubSubService.subscribe("chatroom", message -> {
            sessionManager.broadcast(message);
        });
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        sessionManager.addSession(session);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        sessionManager.removeSession(session);
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        String content = message.getPayload();
        System.out.println("Message beign published on Redis"+content);
        redisPubSubService.publish("chatroom", content);
    }
}

package com.substring.chat.MyChatApp.service;

import org.redisson.api.RTopic;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Service;

import java.util.function.Consumer;

@Service
public class RedisPubSubService {

    private final RedissonClient redissonClient;

    public RedisPubSubService(RedissonClient redissonClient) {
        this.redissonClient = redissonClient;
    }

    public void publish(String topic, String message) {
        RTopic rTopic = redissonClient.getTopic(topic);
        rTopic.publish(message);
    }

    public void subscribe(String topic, Consumer<String> listener) {
        RTopic rTopic = redissonClient.getTopic(topic);
        rTopic.addListener(String.class, (channel, msg) -> listener.accept(msg));
    }
}

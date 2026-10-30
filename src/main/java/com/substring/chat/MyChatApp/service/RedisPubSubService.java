package com.substring.chat.MyChatApp.service;

import org.redisson.api.RTopic;
import org.redisson.api.RedissonClient;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.substring.chat.MyChatApp.repositories.ChatMessageRepository;

import java.util.function.Consumer;

@Service
public class RedisPubSubService {

    private final RedissonClient redissonClient;
    private final KafkaProducer kafkaProducer;
    private final MessageService messageService;

    public RedisPubSubService(RedissonClient redissonClient, MessageService messageService,KafkaProducer kafkaProducer) {
        this.redissonClient = redissonClient;
        this.messageService = messageService;
        this.kafkaProducer = kafkaProducer;
    }

    public void publish(String topic, String message) {
        RTopic rTopic = redissonClient.getTopic(topic);
        //messageService.saveMessage(message);
        kafkaProducer.produceMessage(message);
        rTopic.publish(message);
        
    }

    public void subscribe(String topic, Consumer<String> listener) {
        RTopic rTopic = redissonClient.getTopic(topic);
        rTopic.addListener(String.class, (channel, msg) -> listener.accept(msg));
    }
    // private void forwardToKafkaWithRetry(String kafkaTopic, String message) {
    //     int maxAttempts = 3;
    //     int attempt = 0;
    //     System.out.println(message);
    //     while (attempt < maxAttempts) {
    //         try {
    //             kafkaTemplate.send(kafkaTopic, message).get(); // sync send
    //             break; // success
    //         } catch (Exception ex) {
    //             attempt++;
    //             System.err.println("Kafka send failed (attempt " + attempt + "): " + ex.getMessage());
    //             try {
    //                 Thread.sleep(100 * attempt); // simple backoff
    //             } catch (InterruptedException ignored) {}
    //         }
    //     }
    // }
}

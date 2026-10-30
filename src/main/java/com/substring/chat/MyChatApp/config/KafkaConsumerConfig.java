package com.substring.chat.MyChatApp.config;

import java.io.IOException;
import java.util.*;

import org.apache.kafka.clients.CommonClientConfigs;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.config.SaslConfigs;
import org.apache.kafka.common.config.SslConfigs;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;

@Configuration
@EnableKafka
public class KafkaConsumerConfig {

    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapServers;
    
    @Value("${spring.kafka.properties.sasl.jaas.config}")
    private String saslJaasConfig;
    
    @Value("${spring.kafka.properties.ssl.truststore.location}")
    private String truststoreLocation;
    
    @Value("${spring.kafka.properties.ssl.truststore.password}") 
    private String truststorePassword;

    @Bean
    public ConsumerFactory<String, String> consumerFactory() {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "chat-consumer");
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        
        // Security config (same as producer)
        props.put(CommonClientConfigs.SECURITY_PROTOCOL_CONFIG, "SASL_SSL");
        props.put(SaslConfigs.SASL_MECHANISM, "PLAIN");
        props.put(SaslConfigs.SASL_JAAS_CONFIG, saslJaasConfig);
        
        // SSL config (same as producer)
        String truststorePath1 = truststoreLocation;
        if (truststorePath1.startsWith("classpath:")) {
            truststorePath1 = truststorePath1.replace("classpath:", "");
            try {
                truststorePath1 = new ClassPathResource(truststorePath1).getFile().getAbsolutePath();
            } catch (IOException e) {
                throw new RuntimeException("Failed to load truststore from classpath", e);
            }
        }
        props.put(SslConfigs.SSL_TRUSTSTORE_LOCATION_CONFIG, truststorePath1);
        props.put(SslConfigs.SSL_TRUSTSTORE_PASSWORD_CONFIG, truststorePassword);
        
        return new DefaultKafkaConsumerFactory<>(
            props,
            new StringDeserializer(),
            new StringDeserializer()
        );
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, String> kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, String> factory =
            new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        return factory;
    }
}
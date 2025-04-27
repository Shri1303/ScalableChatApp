package com.substring.chat.MyChatApp.config;


import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RedissonConfig {

    @Value("${redis.host}")
    private String redisHost;

    @Value("${redis.username}")
    private String redisUsername;

    @Value("${redis.password}")
    private String redisPassword;
    @Bean
    public RedissonClient redissonClient() {
        Config config = new Config();
        
        config.useSingleServer()
            .setAddress(redisHost)
            .setUsername(redisUsername)
            .setPassword(redisPassword);
        
        return Redisson.create(config);
    }
}

package com.substring.chat.MyChatApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;

@SpringBootApplication
@EnableKafka
public class MyChatAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyChatAppApplication.class, args);
	}

}

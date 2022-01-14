package com.fsecure.messageboard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource(value = {"classpath:messages.properties"})
public class MessageboardApplication {

	public static void main(String[] args) {
		SpringApplication.run(MessageboardApplication.class, args);
	}
}

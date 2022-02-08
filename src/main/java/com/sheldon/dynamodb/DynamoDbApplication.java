package com.sheldon.dynamodb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.sheldon.dynamodb")
public class DynamoDbApplication {

	public static void main(String[] args) {
		SpringApplication.run(DynamoDbApplication.class, args);
	}

}

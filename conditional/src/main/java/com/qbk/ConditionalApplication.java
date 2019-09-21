package com.qbk;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * 条件装配学习demo
 */
@SpringBootApplication
public class ConditionalApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConditionalApplication.class, args);
	}

}

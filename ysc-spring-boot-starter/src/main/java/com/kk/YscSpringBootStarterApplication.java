package com.kk;

import com.kk.autocinfigure.StarterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;

@SpringBootApplication
public class YscSpringBootStarterApplication {

	@Autowired
	private StarterService starterService;

	@Bean
	public ApplicationRunner runner(){
		return applicationArguments -> {
			String[] splitArray = starterService.split(",");
			System.out.println(Arrays.toString(splitArray));
		};
	}

	public static void main(String[] args) {
		SpringApplication.run(YscSpringBootStarterApplication.class, args);
	}

}

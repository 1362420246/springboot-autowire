package com.kk;

import com.kk.autocinfigure.StarterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;

/**
 * 自定义 Starter项目
 * 通过resources\META-INF\spring.factories中
 * org.springframework.boot.autoconfigure.EnableAutoConfiguration作为key
 * 添加指定的@Configuration配置类
 * 其他项目引入就自动注入@Configuration配置中的bean
 */
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

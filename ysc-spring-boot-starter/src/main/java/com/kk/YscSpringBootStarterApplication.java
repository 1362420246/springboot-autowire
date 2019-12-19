package com.kk;

import com.kk.autocinfigure.StarterService;
import com.kk.mapper.TbUserMaper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

/**
 * 自定义 Starter项目
 * 通过resources\META-INF\spring.factories中
 * org.springframework.boot.autoconfigure.EnableAutoConfiguration作为key
 * 添加指定的@Configuration配置类
 * 其他项目引入就自动注入@Configuration配置中的bean
 */
@RestController
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

	@GetMapping("/get")
	public String[] get(){
		return starterService.split(",");
	}

	@Autowired
	private TbUserMaper tbUserMaper;

	@GetMapping("/get/list")
	public List<String> getList(){
		return tbUserMaper.getList();
	}

	public static void main(String[] args) {
		SpringApplication.run(YscSpringBootStarterApplication.class, args);
	}

}

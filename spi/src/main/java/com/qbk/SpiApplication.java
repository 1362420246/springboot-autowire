package com.qbk;

import com.kk.autocinfigure.StarterService;
import com.qbk.onBean.BpiBean;
import com.qbk.selector.RootBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;

/**
 * 验证 自定义starter项目
 */
@SpringBootApplication
public class SpiApplication {

	/**
	 * 来自ysc-spring-boot-starter项目，自动装配，
	 * 他的package不在本项目的主路径上，如果不配置spring.factories无法加载
	 */
	@Autowired
	private StarterService starterService;

	/**
	 * 来自import项目，能装配是因为他的package在本项目的主路径上
	 */
	@Autowired
	private RootBean rootBean;

	/**
	 * 来自conditional项目，
	 * 能装配是因为他的package在本项目的主路径上
	 * 并且在本项目配置文件中配置了装配条件
	 */
	@Autowired
	private BpiBean bpiBean;

	@Autowired
	private String webBean;

	@Bean
	public ApplicationRunner runner(){

		System.out.println(rootBean);
		System.out.println(bpiBean);
		System.out.println(webBean);

		return applicationArguments -> {
			String[] splitArray = starterService.split(",");
			System.out.println(Arrays.toString(splitArray));
		};
	}

	@Bean
	@ConditionalOnWebApplication
	public String webBean(){
		return "web";
	}

	public static void main(String[] args) {
		SpringApplication.run(SpiApplication.class, args);
	}

}

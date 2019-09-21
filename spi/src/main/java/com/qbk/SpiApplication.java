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

@SpringBootApplication
public class SpiApplication {

	@Autowired
	private StarterService starterService;

	@Autowired
	private RootBean rootBean;

	@Autowired
	protected BpiBean bpiBean;

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
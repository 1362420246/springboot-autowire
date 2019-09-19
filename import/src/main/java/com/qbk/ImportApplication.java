package com.qbk;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@SpringBootApplication
public class ImportApplication {

	@Resource(name ="urlConnection")
	private RestTemplate urlConnection;

	public static void main(String[] args) {
		SpringApplication.run(ImportApplication.class, args);
	}

}

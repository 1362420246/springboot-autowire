package com.qbk.active;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@Configuration
//指定环境
@Profile({ "dev" })
public class ProfileConfig {

    @Bean(value = "restTemplate")
    public RestTemplate urlConnectionRestTemplate(){
        System.out.println("加载了：restTemplate");
        return new RestTemplate();
    }
}

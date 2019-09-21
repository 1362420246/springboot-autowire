package com.qbk.active;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

/**
 * 按照指定环境装配bean
 */
@Configuration
@Profile({ "dev" })
public class ProfileConfig {

    @Bean(value = "restTemplate")
    public RestTemplate urlConnectionRestTemplate(){
        System.out.println("加载了：restTemplate");
        return new RestTemplate();
    }
}

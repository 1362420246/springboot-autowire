package com.qbk.config;

import com.qbk.annotation.ImportAnnotation;
import com.qbk.selector.ImportSelectorConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

public class BeanConfig {

    @Bean(value = "urlConnection")
    public RestTemplate urlConnectionRestTemplate(){
        System.out.println("加载了：urlConnection");
        return new RestTemplate(new SimpleClientHttpRequestFactory());
    }
}

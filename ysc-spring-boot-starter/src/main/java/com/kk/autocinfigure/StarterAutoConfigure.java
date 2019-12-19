package com.kk.autocinfigure;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 自动装配的  @Configuration
 */
@Configuration
@MapperScan("com.kk.mapper")//只要是在这个config下扫描的bean都会通过spi被引入项目加载
@ConditionalOnClass(StarterService.class)
@EnableConfigurationProperties(StarterServiceProperties.class)
public class StarterAutoConfigure {

    @Autowired
    private StarterServiceProperties properties;

    /**
     @ConditionalOnClass，当classpath下发现该类的情况下进行自动配置。
     @ConditionalOnMissingBean，当Spring Context中不存在该Bean时。
     @ConditionalOnProperty(prefix = "example.service",value = "enabled",havingValue = "true")，
     当配置文件中example.service.enabled=true时。
     */
    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnProperty(prefix = "example.service", value = "enabled", havingValue = "true")
    StarterService starterService (){
        return new StarterService(properties.getConfig());
    }

}
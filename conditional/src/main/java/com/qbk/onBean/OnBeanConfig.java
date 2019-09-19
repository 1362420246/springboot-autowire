package com.qbk.onBean;


import com.qbk.expression.ApiBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnBean(ApiBean.class)
public class OnBeanConfig {
    @Bean
    public BpiBean bpiBean(){
        return new BpiBean();
    }
}

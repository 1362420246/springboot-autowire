package com.qbk.onBean;


import com.qbk.expression.ApiBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 在当前上下文中存在某个对象时，才会实例化Bean
 */
@Configuration
@ConditionalOnBean(ApiBean.class)
public class OnBeanConfig {
    @Bean
    public BpiBean bpiBean(){
        return new BpiBean();
    }
}

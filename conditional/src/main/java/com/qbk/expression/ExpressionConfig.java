package com.qbk.expression;

import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 当表达式为true的时候，才会实例化一个Bean
 * 表达式读取的配置文件中的配置
 */
@Configuration
@ConditionalOnExpression("${config.enabled:false}")
public class ExpressionConfig {

    @Bean
    public ApiBean apiBean(){
        return new ApiBean();
    }
}

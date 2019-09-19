package com.qbk.expression;

import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnExpression("${config.enabled:false}")
public class ExpressionConfig {

    @Bean
    public ApiBean apiBean(){
        return new ApiBean();
    }
}

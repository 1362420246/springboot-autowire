package com.qbk.customized;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

/**
 * 自定义@Conditional 条件装配bean
 */
@Configuration
@Conditional(InitConditional.class)
public class ConditionalConfig {

    @Bean
    public CustomizedBean customizedBean(){
        return new CustomizedBean();
    }
}

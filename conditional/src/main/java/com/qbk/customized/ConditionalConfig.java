package com.qbk.customized;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

@Configuration
@Conditional(InitConditional.class)
public class ConditionalConfig {

    @Bean
    public CustomizedBean customizedBean(){
        return new CustomizedBean();
    }
}

package com.qbk.customized;


import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

public class InitConditional implements Condition {

    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        //判断是否已经包含了  Bean
        return context.getBeanFactory().containsBean("conditionalApplication");
    }
}

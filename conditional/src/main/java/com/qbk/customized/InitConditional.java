package com.qbk.customized;


import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * 实现Condition接口
 */
public class InitConditional implements Condition {

    /**
     * @param context:判断条件能使用的上下文环境
     * @param metadata:注解所在位置的注释信息
     * */
    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        //获取类加载器
        ClassLoader classLoader = context.getClassLoader();
        //获取当前环境信息
        Environment environment = context.getEnvironment();
        //获取bean定义的注册类
        BeanDefinitionRegistry registry = context.getRegistry();

        //获取ioc使用的beanFactory
        ConfigurableListableBeanFactory beanFactory = context.getBeanFactory();

        //判断是否已经包含了  Bean
        return beanFactory.containsBean("conditionalApplication");
    }
}



package com.qbk;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**

 @ConditionalOnBean（仅仅在当前上下文中存在某个对象时，才会实例化一个Bean）
 @ConditionalOnMissingBean（仅仅在当前上下文中不存在某个对象时，才会实例化一个Bean）
 @ConditionalOnClass（某个class位于类路径上，才会实例化一个Bean）
 @ConditionalOnMissingClass（某个class类路径上不存在的时候，才会实例化一个Bean）
 @ConditionalOnWebApplication:当前项目是Web项目的条件下
 @ConditionalOnNotWebApplication（不是web应用）
 @ConditionalOnExpression（当表达式为true的时候，才会实例化一个Bean）
 @ConditionalOnJava:基于JVM版本作为判断条件
 @ConditionalOnJndi:在JNDI存在的条件下查找指定的位置
 @ConditionalOnProperty:指定的属性是否有指定的值
 @ConditionalOnResource:类路径下是否有指定的资源
 @ConditionalOnSingleCandidate:当指定的Bean在容器中只有一个，或者在有多个Bean的情况下，用来指定首选的Bean
 */
@SpringBootApplication
public class ConditionalApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConditionalApplication.class, args);
	}

}

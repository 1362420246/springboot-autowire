package com.qbk.registrar;


import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * 用@Import导入ImportBeanDefinitionRegistrar实现 注入bean
 */
@Configuration
@Import({BeanDefinitionRegistrar.class})
public class ImportRegistrarConfig {
}

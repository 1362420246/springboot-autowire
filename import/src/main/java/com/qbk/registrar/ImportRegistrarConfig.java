package com.qbk.registrar;


import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({BeanDefinitionRegistrar.class})
public class ImportRegistrarConfig {
}

package com.qbk.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({UserBean.class,BeanConfig.class})
public class ImportConfig {
}

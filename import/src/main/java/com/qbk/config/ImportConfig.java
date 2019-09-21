package com.qbk.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * 用@Import注解导入 普通bean 和 配置类
 */
@Configuration
@Import({UserBean.class,BeanConfig.class})
public class ImportConfig {
}

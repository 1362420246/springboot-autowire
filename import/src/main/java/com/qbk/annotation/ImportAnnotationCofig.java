package com.qbk.annotation;

import com.qbk.selector.ImportSelectorConfig;
import org.springframework.context.annotation.Configuration;

/**
 * 使用自定义@Import的衍生注解 注入bean
 */
@Configuration
@ImportAnnotation(value = "通过注解加载一些bean")
public class ImportAnnotationCofig {
}

package com.qbk.annotation;

import com.qbk.selector.ImportSelectorConfig;
import org.springframework.context.annotation.Configuration;

@Configuration
@ImportAnnotation(value = "通过注解加载一些bean")
public class ImportAnnotationCofig {
}

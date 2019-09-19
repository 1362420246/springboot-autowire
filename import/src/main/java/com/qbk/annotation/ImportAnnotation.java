package com.qbk.annotation;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Import({ImportAnnotationSelector.class})
public @interface ImportAnnotation {
    //配置一些方法
     String value() ;
}

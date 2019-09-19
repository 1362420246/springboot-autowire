package com.qbk.annotation;


import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

import java.util.Map;

public class ImportAnnotationSelector implements ImportSelector
{
    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        Map<String,Object> attributes=
                importingClassMetadata.getAnnotationAttributes(ImportAnnotation.class.getName());
        System.out.println(attributes);
        return new String[0];
    }
}

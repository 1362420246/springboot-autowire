package com.qbk.selector;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

public class BeanImportSelector implements ImportSelector {
    @Override
    public String[] selectImports(AnnotationMetadata annotationMetadata) {
        //返回值，就是到导入到容器中的组件全类名
        return new String[]{RootBean.class.getName()};
    }
}

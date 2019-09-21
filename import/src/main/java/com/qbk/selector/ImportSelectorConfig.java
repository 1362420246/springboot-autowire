package com.qbk.selector;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * 用@Import导入ImportSelector实现 注入bean
 */
@Configuration
@Import({BeanImportSelector.class})
public class ImportSelectorConfig {
}

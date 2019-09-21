package com.kk.autocinfigure;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 读取属性文件中的配置
 */
@ConfigurationProperties("example.service")
public class StarterServiceProperties {

    private String config;

    public void setConfig(String config) {
        this.config = config;
    }

    public String getConfig() {
        return config;
    }
}
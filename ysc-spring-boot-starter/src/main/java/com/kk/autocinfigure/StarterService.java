package com.kk.autocinfigure;

import org.springframework.util.StringUtils;

/**
 * 被注入的bean
 */
public class StarterService {

    private String config;

    public StarterService(String config) {
        this.config = config;
    }

    public String[] split(String separatorChar) {
        return StringUtils.split(this.config, separatorChar);
    }

}
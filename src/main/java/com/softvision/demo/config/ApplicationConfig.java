/*
 * Copyright (C) 2018 Allegiant Travel Company - All Rights Reserved
 *
 * This file is subject to the terms and conditions defined in
 * file 'LICENSE.txt', which is part of this source code package.
 */
package com.softvision.demo.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.*;

import java.util.HashMap;
import java.util.Map;

@Configuration
@ComponentScan("com.softvision.demo")
public class ApplicationConfig {
    private static final Logger logger = LoggerFactory.getLogger(ApplicationConfig.class);

    private Environment env;

    @Autowired
    public ApplicationConfig(Environment env) {
        this.env = env;
    }

    @Bean
    public Map<String, Object> applicationProperties() {
        final Map<String, Object> map = new HashMap<>();
        MutablePropertySources mutablePropertySources = ((AbstractEnvironment) env).getPropertySources();
        mutablePropertySources.forEach(o -> {
            PropertySource propertySource = o;
            String name = propertySource.getName();
            logger.debug("Application Properties, name={}, source={}", name, propertySource.getSource());
            if (propertySource instanceof MapPropertySource && name.contains("applicationConfig:")) {
                logger.debug("adding name={}", name);
                map.putAll(((MapPropertySource) propertySource).getSource());
            }
        });
        return map;
    }
}

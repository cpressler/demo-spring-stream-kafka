/*
 * Copyright (C) 2018 - All Rights Reserved
 *
 * This file is subject to the terms and conditions defined in
 * file 'LICENSE.txt', which is part of this source code package.
 */
package com.softvision.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;
import java.util.List;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    private static final String SERVICE_TITLE = "Demo Spring Cloud Stream Kafka web service";
    private static final String SERVICE_DESCRIPTION = "Test App to demo how to utilize Spring Cloud Stream and Message Brokers";
    private static final String APPLICATION_VERSION = "1";
    private static final String TOS_URL = "";
    private static final String CONTACT_NAME = "Chester Pressler";
    private static final String CONTACT_URL = "";
    private static final String CONTACT_EMAIL = "";
    private static final String APPLICATION_LICENSE = "";
    private static final String LICENSE_URL = "";
    private static final List<VendorExtension> VENDOR_EXTENSIONS = Collections.emptyList();

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .useDefaultResponseMessages(false)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.softvision.demo.controller"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        return new ApiInfo(
                SERVICE_TITLE,
                SERVICE_DESCRIPTION,
                APPLICATION_VERSION,
                TOS_URL,
                new Contact(CONTACT_NAME, CONTACT_URL, CONTACT_EMAIL),
                APPLICATION_LICENSE,
                LICENSE_URL,
                VENDOR_EXTENSIONS
        );
    }
}

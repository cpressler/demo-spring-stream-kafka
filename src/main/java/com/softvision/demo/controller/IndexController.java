/*
 * Copyright (C) 2018 Allegiant Travel Company - All Rights Reserved
 *
 * This file is subject to the terms and conditions defined in
 * file 'LICENSE.txt', which is part of this source code package.
 */
package com.softvision.demo.controller;

import io.swagger.annotations.Api;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.Map;
import java.util.TreeMap;

@Controller
@Api(value = "Index Controller", produces = MediaType.APPLICATION_JSON_VALUE, tags = {"index"})
public class IndexController {
    @Resource(name = "applicationProperties")
    private Map<String, Object> applicationProperties;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("applicationProperties", new TreeMap<>(applicationProperties));
        return "index";
    }
}

package com.syw.blog.console.controller;

import com.syw.blog.service.PowerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/console/powwer")
public class PowerController {

    @Autowired
    private PowerService powerService;

}

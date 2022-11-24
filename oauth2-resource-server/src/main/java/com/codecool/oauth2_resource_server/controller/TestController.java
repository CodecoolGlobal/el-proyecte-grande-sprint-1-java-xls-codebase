package com.codecool.oauth2_resource_server.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/test/resource")
    public String test() {
        return("this is a test from TestController(Resource Server)");
    }
}

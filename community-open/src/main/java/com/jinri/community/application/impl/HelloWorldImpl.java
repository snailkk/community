package com.jinri.community.application.impl;

import com.jinri.community.application.HelloWorld;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldImpl implements HelloWorld {

    @GetMapping("/test")
    public String helloWorld(){
        return "hello world!";
    }
}

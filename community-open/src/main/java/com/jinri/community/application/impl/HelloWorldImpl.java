package com.jinri.community.application.impl;

import com.jinri.community.application.HelloWorld;
import com.jinri.community.infra.oss.util.MinioUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class HelloWorldImpl implements HelloWorld {

    @Resource
    private MinioUtil minioUtil;

    @GetMapping("/test")
    public String helloWorld(){
        return "welcome to my world!";
    }

    @RequestMapping("/testGetAllBuckets")
    public String testGetAllBuckets() throws Exception {
        List<String> allBucket = minioUtil.getAllBucket();
        return allBucket.get(0);
    }
}

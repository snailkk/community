package com.jinri.community.application.impl;

import com.jinri.community.application.HelloWorld;
import com.jinri.community.application.entity.Result;
import com.jinri.community.infra.oss.service.FileService;
import lombok.extern.slf4j.Slf4j;
import com.alibaba.fastjson.JSON;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.InputStream;
import java.util.List;

@RestController
@Slf4j
public class HelloWorldImpl implements HelloWorld {

    @Resource
    private FileService fileService;



    @GetMapping("/test")
    public String helloWorld(){
        return "welcome to my world!";
    }

    @RequestMapping("/testGetAllBuckets")
    public String testGetAllBuckets() throws Exception {
        List<String> allBucket = fileService.getAllBucket();
        return allBucket.get(0);
    }

    @RequestMapping("/getUrl")
    public String getUrl(String bucketName, String objectName) throws Exception {
        return fileService.getUrl(bucketName, objectName);
    }

    @RequestMapping("/downLoad")
    public InputStream downLoad(String bucket, String objectName) {
        return fileService.downLoad(bucket, objectName);
    }

    @RequestMapping("/getName")
    public List<String> getName(@RequestParam String bucket) throws Exception {
        return fileService.getAllFile(bucket);
    }

    /**
     * 上传文件
     */
    @RequestMapping("/upload")
    public Result upload(MultipartFile uploadFile, String bucket, String objectName) throws Exception {
        try {
            String url = fileService.uploadFile(uploadFile, bucket, objectName);
            return Result.ok(url);
        } catch (Exception e) {
            log.error("An exception occurred:", e);
        }
        return Result.fail();
    }
}

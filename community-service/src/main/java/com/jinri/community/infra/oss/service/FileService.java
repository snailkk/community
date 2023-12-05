package com.jinri.community.infra.oss.service;

import com.jinri.community.infra.oss.adapter.StorageAdapter;
import com.jinri.community.infra.oss.entity.FileInfo;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 文件存储service
 *
 * @author: kangwsh22@163.com
 * @date: 2023/12/6
 */
@Service
public class FileService {

    private final StorageAdapter storageAdapter;

    public FileService(StorageAdapter storageAdapter) {
        this.storageAdapter = storageAdapter;
    }

    /**
     * 列出所有桶
     */
    public List<String> getAllBucket() {
        return storageAdapter.getAllBucket();
    }

    /**
     * 列出当前桶及文件
     */
    public List<String> getAllFile(String bucket) {
        List<FileInfo> files = storageAdapter.getAllFile(bucket);
        return files.stream().map(s -> s.getFileName()).collect(Collectors.toList());
    }

    /**
     * 下载文件
     */
    public InputStream downLoad(String bucket, String objectName) {
        return storageAdapter.downLoad(bucket, objectName);
    }

    /**
     * 获取文件路径
     */
    public String getUrl(String bucketName,String objectName) {
        return storageAdapter.getUrl(bucketName,objectName);
    }

    /**
     * 上传文件
     */
    public String uploadFile(MultipartFile uploadFile, String bucket, String objectName){
        storageAdapter.uploadFile(uploadFile,bucket,objectName);
        objectName = objectName + "/" + uploadFile.getOriginalFilename();
        return storageAdapter.getUrl(bucket, objectName);
    }
}
package com.learn.controller;

import com.learn.pojo.Result;
import com.learn.utils.AliOSSUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Slf4j
@RestController
public class UploadController {

//    @PostMapping("/upload")
//    public Result upload(String username, Integer age, MultipartFile image){
//        log.info("文件上传:{}, {}, {}", username, age, image);
//        return Result.success();
//    }

    @Autowired
    private AliOSSUtils aliOSSUtils;

    @PostMapping("/upload")
    public Result upload(MultipartFile image) throws IOException {
        log.info("文件上传:{}", image.getOriginalFilename());
        //调用阿里云OSS工具类进行文件上传
        String url = aliOSSUtils.upload(image);
        log.info("文件上传完成，文件访问的url为：{}", url);
        return Result.success(url);
    }
}

package com.learn.utils;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "aliyun.oss")
public class AliOSSProperties {
    public String endpoint;
    public String accessKeyId;
    public String accessKeySecret;
    public String bucketName;
}

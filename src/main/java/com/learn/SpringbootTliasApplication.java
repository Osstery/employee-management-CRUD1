package com.learn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@ServletComponentScan //当前项目开启了对Servlet组件的支持
@SpringBootApplication
public class SpringbootTliasApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootTliasApplication.class, args);
    }

}

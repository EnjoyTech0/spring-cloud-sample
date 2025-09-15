package com.enjoy.springcloud.lb._static.service.s1;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@PropertySource("classpath:application1.properties")
public class StaticServiceApp1 {
    @Value("${server.port}")
    private String port;

    public static void main(String[] args) {
        SpringApplication.run(StaticServiceApp1.class, args);
    }

    @GetMapping("/hello")
    public String index() {
        return "hello world" + port;
    }
}
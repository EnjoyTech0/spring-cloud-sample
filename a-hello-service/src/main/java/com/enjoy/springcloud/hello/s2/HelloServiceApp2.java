package com.enjoy.springcloud.hello.s2;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@PropertySource("classpath:application2.properties")
public class HelloServiceApp2 {
    @Value("${server.port}")
    private String port;

    public static void main(String[] args) {
        SpringApplication.run(HelloServiceApp2.class, args);
    }

    @GetMapping("/hello")
    public String index() {
        return "hello world: " + port;
    }
}
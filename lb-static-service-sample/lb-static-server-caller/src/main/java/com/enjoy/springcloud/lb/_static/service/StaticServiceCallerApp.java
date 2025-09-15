package com.enjoy.springcloud.lb._static.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class StaticServiceCallerApp {
    public static void main(String[] args) {
        SpringApplication.run(StaticServiceCallerApp.class, args);
    }
}
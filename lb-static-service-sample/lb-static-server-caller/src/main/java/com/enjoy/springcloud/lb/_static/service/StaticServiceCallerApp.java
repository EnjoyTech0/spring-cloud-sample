package com.enjoy.springcloud.lb._static.service;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
@EnableFeignClients
public class StaticServiceCallerApp {
    public static void main(String[] args) {
        SpringApplication.run(StaticServiceCallerApp.class, args);
    }

    @Configuration
    @LoadBalancerClient(name = "static-service", configuration = com.enjoy.springcloud.lb._static.service.LoadBalancerConfiguration.class)
    public static class MyConfiguration {
    }
}
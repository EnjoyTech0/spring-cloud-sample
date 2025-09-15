package com.enjoy.springcloud.lb._static.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "static-service")
public interface StaticServiceClient {
    @GetMapping("/hello")
    String sayHello();
}
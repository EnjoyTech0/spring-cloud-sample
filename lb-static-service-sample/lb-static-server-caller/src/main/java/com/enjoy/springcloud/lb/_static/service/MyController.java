package com.enjoy.springcloud.lb._static.service;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyController {

    private final StaticServiceClient staticServiceClient;

    public MyController(StaticServiceClient staticServiceClient) {
        this.staticServiceClient = staticServiceClient;
    }

    @GetMapping("/callStaticService")
    public String callService() {
        // FeignClient 会自动进行负载均衡调用
        return "Calling static-service: " + staticServiceClient.sayHello();
    }
}
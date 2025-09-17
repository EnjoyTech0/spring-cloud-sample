package com.enjoy.springcloud.circuitbreaker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@EnableRetry
@RestController
@SpringBootApplication
public class SpringRetryCircuitBreakerSampleApplication {
    private final HelloCircuitBreakerService helloCircuitBreakerService;
    private final HelloRetryAndCircuitBreakerService helloRetryAndCircuitBreakerService;

    public SpringRetryCircuitBreakerSampleApplication(HelloCircuitBreakerService helloCircuitBreakerService, HelloRetryAndCircuitBreakerService helloRetryAndCircuitBreakerService) {
        this.helloCircuitBreakerService = helloCircuitBreakerService;
        this.helloRetryAndCircuitBreakerService = helloRetryAndCircuitBreakerService;
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringRetryCircuitBreakerSampleApplication.class, args);
    }

    @GetMapping("/hello")
    public String hello() {
        return helloCircuitBreakerService.hello();
    }

    @GetMapping("/hello1")
    public String hello1() {
        return helloRetryAndCircuitBreakerService.hello();
    }
}
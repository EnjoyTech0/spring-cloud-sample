package com.enjoy.springcloud.resilience4j_feign_nacos;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(
        name = "payment-service",
        url = "http://localhost:8080",
        path = "/payment",
        fallback = PaymentFallback.class
        // qualifiers = "payment-service-circuitbreaker"
)
public interface PaymentFeignClient {

    @GetMapping(value = "/pay")
    String pay();
}
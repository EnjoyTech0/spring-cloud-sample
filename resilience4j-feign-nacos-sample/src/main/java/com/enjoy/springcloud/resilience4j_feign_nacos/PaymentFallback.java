package com.enjoy.springcloud.resilience4j_feign_nacos;

import org.springframework.stereotype.Component;

@Component
public class PaymentFallback implements PaymentFeignClient {
    @Override
    public String pay() {
        return "Sorry, payment service is not available. This is a fallback response.";
    }
}
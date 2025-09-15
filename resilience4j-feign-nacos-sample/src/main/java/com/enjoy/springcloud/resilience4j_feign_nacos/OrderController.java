package com.enjoy.springcloud.resilience4j_feign_nacos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private PaymentFeignClient paymentFeignClient;

    @GetMapping("/create")
    public String createOrder() {
        // 调用支付服务，如果失败会触发熔断
        long start = System.currentTimeMillis();
        String paymentResult = paymentFeignClient.pay();
        System.out.println("Time taken: " + (System.currentTimeMillis() - start));
        return "Order created. Payment result: " + paymentResult;
    }
}
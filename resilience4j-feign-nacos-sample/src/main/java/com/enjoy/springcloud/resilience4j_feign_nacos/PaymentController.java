package com.enjoy.springcloud.resilience4j_feign_nacos;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payment")
public class PaymentController {

    @GetMapping("/pay")
    public String pay() throws InterruptedException {
        Thread.sleep(2000);
        return "Payment successful!";
    }
}
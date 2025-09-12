package com.enjoy.springcloud.provider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class ProviderBootStarter {
    public static void main(String[] args) throws InterruptedException {
        SpringApplication.run(ProviderBootStarter.class, args);
    }

    @GetMapping(value = "/recommended")
    public String readingList() throws InterruptedException {
        Thread.sleep(2000);
        return "Spring in Action (Manning), Cloud Native Java (O'Reilly), Learning Spring Boot (Packt)";
    }
}
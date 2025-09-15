package com.enjoy.springcloud.resilience4j_nacos;

import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import io.github.resilience4j.common.circuitbreaker.configuration.CircuitBreakerConfigurationProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@SpringBootApplication
public class ConsumerBootStarter {
    @Autowired
    private BookService bookService;

    public static void main(String[] args) throws InterruptedException {
        SpringApplication.run(ConsumerBootStarter.class, args);
    }

    @GetMapping("/to-read")
    public String toRead() {
        return bookService.readingList();
    }

    @Configurable
    public static class Config {
        @Bean
        public RestTemplate restTemplate() {
            return new RestTemplate();
        }

        @Bean
        @RefreshScope
        public CircuitBreakerRegistry circuitBreakerRegistry(CircuitBreakerConfigurationProperties properties) {
            return CircuitBreakerRegistry.of(properties);
        }
    }
}
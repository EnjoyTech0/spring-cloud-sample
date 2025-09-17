package com.enjoy.springcloud.circuitbreaker.cloud;

import com.enjoy.springcloud.circuitbreaker.RemoteAccessException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.circuitbreaker.springretry.SpringRetryCircuitBreakerFactory;
import org.springframework.cloud.circuitbreaker.springretry.SpringRetryConfigBuilder;
import org.springframework.cloud.client.circuitbreaker.Customizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.retry.policy.SimpleRetryPolicy;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

// @EnableRetry
@RestController
@SpringBootApplication
public class SpringCloudRetryCircuitBreakerApplication {
    private final HelloCircuitBreakerService helloCircuitBreakerService;

    public SpringCloudRetryCircuitBreakerApplication(HelloCircuitBreakerService helloCircuitBreakerService) {
        this.helloCircuitBreakerService = helloCircuitBreakerService;
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringCloudRetryCircuitBreakerApplication.class, args);
    }

    @GetMapping("/hello")
    public String hello() {
        return helloCircuitBreakerService.hello();
    }

    @Configuration
    static class Config {
        @Bean
        public Customizer<SpringRetryCircuitBreakerFactory> defaultCustomizer() {
            return factory -> factory.configureDefault(id -> new SpringRetryConfigBuilder(id)
                    .retryPolicy(new SimpleRetryPolicy(3,
                            Collections.singletonMap(RemoteAccessException.class, true) // 指定哪些异常可重试
                    )).build());
        }
    }
}
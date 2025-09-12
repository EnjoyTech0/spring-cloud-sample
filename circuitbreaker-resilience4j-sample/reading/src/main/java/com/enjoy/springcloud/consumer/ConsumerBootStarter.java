package com.enjoy.springcloud.consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
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
        // @Bean
        // public Customizer<Resilience4JCircuitBreakerFactory> defaultCustomizer() {
        //     return factory -> factory.configureDefault(id -> new Resilience4JConfigBuilder(id)
        //             .timeLimiterConfig(TimeLimiterConfig.custom().timeoutDuration(Duration.ofSeconds(3)).build())
        //             .circuitBreakerConfig(CircuitBreakerConfig.custom()
        //                     .failureRateThreshold(50)
        //                     .waitDurationInOpenState(Duration.ofMillis(1000))
        //                     .slidingWindowSize(2)
        //                     .build())
        //             .build());
        // }

        @Bean
        public RestTemplate restTemplate() {
            return new RestTemplate();
        }
    }
}
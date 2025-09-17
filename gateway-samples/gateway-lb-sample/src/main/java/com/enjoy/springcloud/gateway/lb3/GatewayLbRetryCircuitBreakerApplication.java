package com.enjoy.springcloud.gateway.lb3;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.circuitbreaker.springretry.SpringRetryCircuitBreakerFactory;
import org.springframework.cloud.circuitbreaker.springretry.SpringRetryConfigBuilder;
import org.springframework.cloud.client.circuitbreaker.Customizer;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.retry.policy.SimpleRetryPolicy;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableRetry
@SpringBootApplication
public class GatewayLbRetryCircuitBreakerApplication {
    public static void main(String[] args) {
        new SpringApplicationBuilder(GatewayLbRetryCircuitBreakerApplication.class)
                .profiles("lb3")
                .run(args);
    }

    // @GetMapping("/fallback")
    // public ResponseEntity<String> fallback(ServerHttpRequest request) {
    //     HttpHeaders headers = request.getHeaders();
    //
    //     String exceptionType = headers.getFirst("FallbackExecutionExceptionType");
    //     String exceptionMessage = headers.getFirst("FallbackExecutionExceptionMessage");
    //     String statusCode = headers.getFirst("FallbackExecutionStatusCode");
    //
    //     return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
    //             .body("--- fallback called ---\n"
    //                     + "exceptionType=" + exceptionType + "\n"
    //                     + "exceptionMessage=" + exceptionMessage + "\n"
    //                     + "statusCode=" + statusCode);
    // }

    @GetMapping("/fallback")
    public ResponseEntity<String> fallback() {
        System.out.println("--- Fallback called ---");
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body("--- Fallback called ---");
    }

    @Configurable
    public static class Config {
        // @Bean
        // public Customizer<ReactiveResilience4JCircuitBreakerFactory> defaultCustomizer() {
        //     return factory -> factory.configureDefault(id -> new Resilience4JConfigBuilder(id)
        //
        //             .circuitBreakerConfig(CircuitBreakerConfig.ofDefaults())
        //             .timeLimiterConfig(TimeLimiterConfig.custom().timeoutDuration(Duration.ofSeconds(4)).build()).build());
        // }

        @Bean
        public Customizer<SpringRetryCircuitBreakerFactory> defaultCustomizer() {
            return factory -> factory.configureDefault(id -> new SpringRetryConfigBuilder(id)
                    // .retryPolicy(new TimeoutRetryPolicy())
                    .retryPolicy(new SimpleRetryPolicy(1))
                    .build());
        }

        // @Bean
        // public Customizer<SpringRetryCircuitBreakerFactory> slowCustomizer() {
        //     return factory -> factory.configure(builder -> builder.retryPolicy(new SimpleRetryPolicy(1)).build(), "slow");
        // }
    }
}
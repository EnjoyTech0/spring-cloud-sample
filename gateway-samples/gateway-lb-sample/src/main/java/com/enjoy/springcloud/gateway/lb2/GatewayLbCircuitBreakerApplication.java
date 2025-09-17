package com.enjoy.springcloud.gateway.lb2;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.gateway.filter.factory.SpringCloudCircuitBreakerFilterFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 一个最基本的 Gateway+lb+CircuitBreaker的例子
 * <p>
 * 启用 CircuitBreaker 需要额外添加 spring-cloud-starter-circuitbreaker-reactor-resilience4j 依赖
 * <p>
 * {@link SpringCloudCircuitBreakerFilterFactory}
 *
 * @see <a href= "https://docs.spring.io/spring-cloud-gateway/docs/3.1.9/reference/html/#spring-cloud-circuitbreaker-filter-factory">GatewayFilter:SpringCloudCircuitBreakerFilterFactory</a>
 * @see <a href= "https://docs.spring.io/spring-cloud-commons/docs/3.1.8/reference/html/#simplediscoveryclient">Load Balancer:SimpleDiscoveryClient</a>
 */
@RestController
@SpringBootApplication
public class GatewayLbCircuitBreakerApplication {
    public static void main(String[] args) {
        new SpringApplicationBuilder(GatewayLbCircuitBreakerApplication.class)
                .profiles("lb2")
                .run(args);
    }

    @GetMapping("/fallback")
    public ResponseEntity<String> fallback() {
        System.out.println("--- Fallback called ---");
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Service error");
    }
}
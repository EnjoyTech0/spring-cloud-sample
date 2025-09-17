package com.enjoy.springcloud.circuitbreaker.cloud;

import com.enjoy.springcloud.circuitbreaker.RemoteAccessException;
import org.springframework.cloud.client.circuitbreaker.CircuitBreaker;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.stereotype.Service;

/**
 * 单独使用 @CircuitBreaker
 */
@Service
public class HelloCircuitBreakerService {
    private final CircuitBreakerFactory<?, ?> circuitBreakerFactory;

    public HelloCircuitBreakerService(CircuitBreakerFactory<?, ?> circuitBreakerFactory) {
        this.circuitBreakerFactory = circuitBreakerFactory;
    }

    public String hello() {
        CircuitBreaker circuitBreaker = circuitBreakerFactory.create("helloService");

        return circuitBreaker.run(
                () -> {
                    System.out.println("调用远程方法...");
                    throw new RemoteAccessException("模拟远程业务异常");
                }
                ,
                throwable -> {
                    System.out.println("进入 fallback: " + throwable.getMessage());
                    return "fallback result";
                }
        );
    }
}
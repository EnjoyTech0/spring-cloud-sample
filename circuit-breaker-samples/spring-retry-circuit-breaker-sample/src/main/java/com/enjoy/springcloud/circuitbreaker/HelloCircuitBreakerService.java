package com.enjoy.springcloud.circuitbreaker;

import org.springframework.retry.annotation.CircuitBreaker;
import org.springframework.retry.annotation.Recover;
import org.springframework.stereotype.Service;

/**
 * 单独使用 @CircuitBreaker
 */
@Service
public class HelloCircuitBreakerService {
    @CircuitBreaker(maxAttempts = 3, openTimeout = 5000, resetTimeout = 10000)
    // @CircuitBreaker 并不会做retry，而是表示请求这个方法在5秒（openTimeout）内失败 3 次（maxAttempts），
    // 则进入熔断状态10秒（resetTimeout）直接执行 recover 方法
    public String hello() {
        System.out.println("helloCircuitBreaker");
        throw new RemoteAccessException("模拟远程业务异常");
    }

    @Recover
    public String recover(Throwable t) {
        return "fallback: cb";
    }
}
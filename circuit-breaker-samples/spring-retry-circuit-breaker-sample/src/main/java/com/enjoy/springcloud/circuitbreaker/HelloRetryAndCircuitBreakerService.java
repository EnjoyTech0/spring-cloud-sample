package com.enjoy.springcloud.circuitbreaker;

import org.springframework.retry.annotation.CircuitBreaker;
import org.springframework.retry.annotation.Recover;
import org.springframework.stereotype.Service;

/**
 * 组合 @Retryable + @CircuitBreaker 使用
 * <p>
 * 注意：1. @Retryable 和 @CircuitBreaker 不能一起使用，例如
 * <blockquote><pre>
 *     {@code @CircuitBreaker}(maxAttempts = 1, openTimeout = 5000, resetTimeout = 10000)
 *     {@code @Retryable}(value = {RemoteAccessException.class}, maxAttempts = 3)
 *     public String hello() {
 *         return helloRemoteService.hello();
 *     }
 * </pre></blockquote>
 * 这种方式不方便还是推荐使用 Resilience4j
 * <p>
 * 2. @Retryable 不能有 @Recover  方法兜底，否则@CircuitBreaker 感知不到异常会认为是正常请求
 */
@Service
public class HelloRetryAndCircuitBreakerService {
    private final HelloRemoteService helloRemoteService;

    public HelloRetryAndCircuitBreakerService(HelloRemoteService helloRemoteService) {
        this.helloRemoteService = helloRemoteService;
    }

    @CircuitBreaker(maxAttempts = 1, openTimeout = 5000, resetTimeout = 10000)
    public String hello() {
        return helloRemoteService.hello();
    }

    @Recover
    public String recover(Throwable t) {
        return "fallback: retry + cb";
    }
}
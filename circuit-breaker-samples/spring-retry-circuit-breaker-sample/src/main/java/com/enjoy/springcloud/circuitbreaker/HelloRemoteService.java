package com.enjoy.springcloud.circuitbreaker;

import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

@Service
public class HelloRemoteService {
    @Retryable(value = {RemoteAccessException.class}, maxAttempts = 3)
    // @Retryable 会做retry，表示请求这个方法失败 3 次，则进入 recover 方法（如果有匹配的）
    public String hello() {
        System.out.println("hello remote service");
        throw new RemoteAccessException("模拟远程业务异常");
    }
}
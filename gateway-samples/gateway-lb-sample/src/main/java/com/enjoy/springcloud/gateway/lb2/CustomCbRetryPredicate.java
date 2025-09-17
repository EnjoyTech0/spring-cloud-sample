package com.enjoy.springcloud.gateway.lb2;

import java.util.function.Predicate;

public class CustomCbRetryPredicate implements Predicate<Throwable> {

    @Override
    public boolean test(Throwable throwable) {
        if (throwable instanceof IllegalArgumentException) {
            return true;
        }
        // 其他异常不重试
        return false;
    }
}
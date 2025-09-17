package com.enjoy.springcloud.circuitbreaker;

public class RemoteAccessException extends RuntimeException {
    public RemoteAccessException(String message) {
        super(message);
    }
}
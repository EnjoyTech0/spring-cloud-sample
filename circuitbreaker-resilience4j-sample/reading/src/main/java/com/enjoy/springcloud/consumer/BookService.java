package com.enjoy.springcloud.consumer;

import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JCircuitBreakerFactory;
import org.springframework.cloud.client.circuitbreaker.CircuitBreaker;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class BookService {

    private final RestTemplate restTemplate;
    private final Resilience4JCircuitBreakerFactory factory;

    public BookService(RestTemplate restTemplate, Resilience4JCircuitBreakerFactory factory) {
        this.restTemplate = restTemplate;
        this.factory = factory;
    }

    public String readingList() {
        long start = System.currentTimeMillis();

        CircuitBreaker circuitBreaker;
        circuitBreaker = factory.create("recommended");

        String res = circuitBreaker.run(
                () -> restTemplate.getForObject("http://localhost:8081/recommended", String.class),
                throwable -> {
                    // LOG.warn("Error making request to book service", throwable);
                    return "Cloud Native Java (O'Reilly)";
                }
        );
        System.out.println("Time taken: " + (System.currentTimeMillis() - start));
        return res;
    }
}
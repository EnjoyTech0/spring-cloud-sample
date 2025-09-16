package com.enjoy.springcloud.gateway.lb1;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

/**
 * 一个最基本的 Gateway+lb的例子，负载均衡算法默认为轮询
 * <p>
 * 只需要spring-cloud-starter-gateway和spring-cloud-starter-loadbalancer两个依赖
 * <p>
 * ref:
 * <p>
 * - <a href= "https://docs.spring.io/spring-cloud-gateway/docs/3.1.9/reference/html/#reactive-loadbalancer-client-filter">GlobalFilter:ReactiveLoadBalancerClientFilter</a>
 * <p>
 * - <a href= "https://docs.spring.io/spring-cloud-commons/docs/3.1.8/reference/html/#simplediscoveryclient">Load Balancer:SimpleDiscoveryClient</a>
 */
@SpringBootApplication
public class GatewayLbApplication {
    public static void main(String[] args) {
        new SpringApplicationBuilder(GatewayLbApplication.class)
                .profiles("lb1")
                .run(args);
    }
}
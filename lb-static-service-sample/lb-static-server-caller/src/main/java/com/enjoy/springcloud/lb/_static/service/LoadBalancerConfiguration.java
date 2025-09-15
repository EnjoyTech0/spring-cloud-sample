// package com.enjoy.springcloud.lb._static.service;
//
// import org.springframework.beans.factory.ObjectProvider;
// import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
// import org.springframework.cloud.loadbalancer.core.RandomLoadBalancer;
// import org.springframework.cloud.loadbalancer.core.ReactorServiceInstanceLoadBalancer;
// import org.springframework.cloud.loadbalancer.core.ServiceInstanceListSupplier;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
//
// @Configuration
// @LoadBalancerClient(name = "static-service", configuration = LoadBalancerConfiguration.class)
// public class LoadBalancerConfiguration {
//
//     @Bean
//     public ReactorServiceInstanceLoadBalancer customLoadBalancer(
//             ObjectProvider<ServiceInstanceListSupplier> serviceInstanceListSupplierProvider) {
//         // 使用 RandomLoadBalancer 替换默认的轮询策略
//         return new RandomLoadBalancer(serviceInstanceListSupplierProvider, "static-service");
//     }
// }
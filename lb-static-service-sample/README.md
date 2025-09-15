轮询的配置

```yaml
spring:
  cloud:
    loadbalancer:
      enabled: true
    # spring-cloud-commons 静态服务配置
    discovery:
      client:
        simple:
          instances:
            # 这里的 'static-service' 就是你的服务名
            static-service:
              - uri: http://localhost:8001
              - uri: http://localhost:8002
              - uri: http://localhost:8003
```

随机的配置
```java
public class LoadBalancerConfiguration {
    @Bean
    ReactorLoadBalancer<ServiceInstance> randomLoadBalancer(Environment environment,
                                                            LoadBalancerClientFactory loadBalancerClientFactory) {
        String name = environment.getProperty(LoadBalancerClientFactory.PROPERTY_NAME);
        return new RandomLoadBalancer(loadBalancerClientFactory
                .getLazyProvider(name, ServiceInstanceListSupplier.class),
                name);
    }
}

@Configuration
@LoadBalancerClient(name = "static-service", configuration = com.enjoy.springcloud.lb._static.service.LoadBalancerConfiguration.class)
public static class MyConfiguration {
}
```
loadbalancer service Supplier 是懒加载的，当前配置在第一次访问的时候才会被创建，看如下配置类：
org.springframework.cloud.loadbalancer.annotation.LoadBalancerClientConfiguration.BlockingSupportConfiguration#discoveryClientServiceInstanceListSupplier
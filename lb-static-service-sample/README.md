轮询的配置

```yaml
spring:
  cloud:
    loadbalancer:
      enabled: true
    # 静态服务配置
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
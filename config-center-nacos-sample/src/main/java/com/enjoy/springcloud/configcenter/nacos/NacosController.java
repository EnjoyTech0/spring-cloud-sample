package com.enjoy.springcloud.configcenter.nacos;

import com.alibaba.cloud.nacos.annotation.NacosConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope
public class NacosController {
    @Value("${plainKey:111}")
    String testKey;

    @NacosConfig(dataId = "spring-cloud-nacos-demo", group = "DEFAULT_GROUP", key = "rate")
    String rate;

    @GetMapping("/nacos")
    public String index() {
        return testKey + ":" + rate;
    }
}
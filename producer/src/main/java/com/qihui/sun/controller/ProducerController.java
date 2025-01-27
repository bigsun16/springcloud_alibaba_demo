package com.qihui.sun.controller;

import com.qihui.sun.config.TestConfigurationProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope//刷新配置信息
public class ProducerController {

    @Value("${message}")
    private String message;
    @Value("${num}")
    private String num;
    @Value("${server.port}")
    private int port;

    //使用了@ConfigurationProperties后去配置，就不需要@RefreshScope注解了
    @Autowired
    private TestConfigurationProperties testConfigurationProperties;

    @GetMapping("/test/{id}")
    public String test(@PathVariable("id") String id) {
        return testConfigurationProperties.getName()+"============"+testConfigurationProperties.getAge()+"====="+id;
    }
    @GetMapping("/getMessage")
    public String getMessage() {
        System.out.println("--------------"+port);
        return message + num + "ddd";
    }

}

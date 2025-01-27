package com.qihui.sun.controller;

import com.qihui.sun.service.openFeign.TestOpenFeignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RefreshScope
public class ConsumerController {

    @Value("${message}")
    private String message;
    @Value("${num}")
    private String num;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private TestOpenFeignService testOpenFeignService;

    @GetMapping("/getMessage")
    public String getMessage() {
        return message + num;
    }

    @GetMapping("/get")
    public String get() {
        String url = "http://producer/getMessage";
        System.out.println(url);
        return restTemplate.getForObject(url, String.class);
    }

    @GetMapping("/test/{id}")
    public String getOpenFeign(@PathVariable("id") String id) {
        return testOpenFeignService.test(id);
    }

}

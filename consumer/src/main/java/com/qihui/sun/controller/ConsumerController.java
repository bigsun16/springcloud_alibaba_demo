package com.qihui.sun.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
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
    private DiscoveryClient discoveryClient;

    @Autowired
    private RestTemplate restTemplate;


    @GetMapping("/getMessage")
    public String getMessage() {
        return message + num;
    }

    @GetMapping("/get")
    public String get() {
        ServiceInstance producer = discoveryClient.getInstances("producer").get(0);
        String url = "http://" + producer.getHost() + ":" + producer.getPort() + "/getMessage";
        return restTemplate.getForObject(url, String.class);
    }

    @GetMapping("/hello")
    public String discoveryTest() {
        return restTemplate.getForObject("http://producer/getMessage", String.class);
    }
}

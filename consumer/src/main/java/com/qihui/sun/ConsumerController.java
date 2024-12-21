package com.qihui.sun;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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


    @GetMapping("/getMessage")
    public String getMessage() {
        return message + num;
    }

    @GetMapping("/hello")
    public String discoveryTest() {
    	return restTemplate.getForObject("http://producer/getMessage", String.class);
    }
}

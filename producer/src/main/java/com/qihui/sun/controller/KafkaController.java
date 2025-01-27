package com.qihui.sun.controller;

import lombok.Setter;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Setter
@RestController
@RequestMapping("/kafka")
public class KafkaController {
    private KafkaTemplate<String, String> kafkaTemplate;

    @GetMapping("/send/{message}")
    public void sendKafkaMessage(@PathVariable("message") String message) {
        kafkaTemplate.send("testTopic", 0, "key", message);
    }
}

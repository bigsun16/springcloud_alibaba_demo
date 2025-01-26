package com.qihui.sun;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope
public class ProducerController {

    @Value("${message}")
    private String message;
    @Value("${num}")
    private String num;

    private KafkaTemplate<String, String> kafkaTemplate;

    public ProducerController(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @GetMapping("/getMessage")
    public String getMessage() {
        return message + num + "ddd";
    }

    @GetMapping("/sendKafkaMessage")
    public void sendKafkaMessage() {
        kafkaTemplate.send("testTopic", 0, "key", "this is a message");
    }
}

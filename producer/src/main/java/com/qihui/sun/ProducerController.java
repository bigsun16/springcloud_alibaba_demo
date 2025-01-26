package com.qihui.sun;

import com.qihui.sun.config.TestConfigurationProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
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
    private KafkaTemplate<String, String> kafkaTemplate;

    public ProducerController(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @GetMapping("/test")
    public String test() {
        return testConfigurationProperties.getName()+"============"+testConfigurationProperties.getAge();
    }
    @GetMapping("/getMessage")
    public String getMessage() {
        System.out.println("--------------"+port);
        return message + num + "ddd";
    }

    @GetMapping("/sendKafkaMessage")
    public void sendKafkaMessage() {
        kafkaTemplate.send("testTopic", 0, "key", "this is a message");
    }
}

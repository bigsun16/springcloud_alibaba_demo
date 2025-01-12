package com.qihui.sun.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KafkaConfig {

    @Bean
    public NewTopic topic() {
        // 创建主题，分区数为2，副本因子为1
        return new NewTopic("testTopic", 2, (short) 1);
    }
}

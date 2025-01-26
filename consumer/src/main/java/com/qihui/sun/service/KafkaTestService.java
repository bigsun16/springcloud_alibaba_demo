package com.qihui.sun.service;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

@Component
public class KafkaTestService {

    @KafkaListener(topics = "testTopic", groupId = "consumer-group")
    public void consumer(ConsumerRecord<String, String> record, Acknowledgment ack) {
        System.out.println(record.value());
        ack.acknowledge();
    }

/*@KafkaListener(topics = "testTopic", groupId = "consumer-group2")
    public void consumer2(ConsumerRecords<String, String> records, Acknowledgment ack) {
        System.out.println(records.count());
        ack.acknowledge();
    }*/

}

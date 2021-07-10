package com.easy.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * consumer
 *
 * @author tallon
 * @version v1.0.0
 * @date 2021-07-10 13:42
 */
@Component
public class KafkaConsumer {
    @KafkaListener(topics = {"spring_kafka"})
    public void consumer(String msg) {
        System.out.printf("consumer msg is : %s\r\n ", msg);
    }
}

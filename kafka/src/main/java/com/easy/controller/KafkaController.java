package com.easy.controller;

import com.easy.producer.KafkaProducer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * controller
 *
 * @author tallon
 * @version v1.0.0
 * @date 2021-07-10 13:46
 */
@RestController
public class KafkaController {

    @Resource
    private KafkaProducer kafkaProducer;

    @GetMapping("/send/{msg}")
    public String send(@PathVariable("msg") String msg) {
        kafkaProducer.send("spring_kafka", msg);
        return "发送成功";
    }
}

package com.company.project;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.company.project.model.Message;

@Component
public class Receiver {
	@KafkaListener(topics = "test")
    public void processMessage(String content) {
        Message m = JSON.parseObject(content, Message.class);
    }
}

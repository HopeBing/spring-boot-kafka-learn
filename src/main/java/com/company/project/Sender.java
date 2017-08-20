package com.company.project;

import java.util.Date;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.company.project.model.Message;

@Component
public class Sender {
	@Autowired
    private KafkaTemplate kafkaTemplate;

    public void sendMessage(){
        Message m = new Message();
        m.setId(System.currentTimeMillis());
        m.setMsg(UUID.randomUUID().toString());
        m.setSendTime(new Date());
        kafkaTemplate.send("test", JSON.toJSONString(m));
    }
    
    public void sendMessage(String message){
    	System.out.println("kafkaTemplate-sendMessage");
        Message m = new Message();
        m.setId(System.currentTimeMillis());
        m.setMsg(message);
        m.setSendTime(new Date());
        kafkaTemplate.send("test", JSON.toJSONString(m));
    }
}

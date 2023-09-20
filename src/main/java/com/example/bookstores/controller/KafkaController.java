package com.example.bookstores.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class KafkaController {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

//    @PostMapping("/send")
//    public void sendMessage(@RequestBody String message) {
//        System.out.println("Get Message from controller: " + message);
//        kafkaTemplate.send("my-topic", message);
//    }

}

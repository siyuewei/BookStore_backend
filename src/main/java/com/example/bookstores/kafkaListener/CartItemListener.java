package com.example.bookstores.kafkaListener;

import com.example.bookstores.service.CartItemService;
import com.example.bookstores.util.websocket.WebSocketServer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class CartItemListener {
    private final CartItemService cartItemService;
    private final KafkaTemplate<String, Long> kafkaTemplate;
    private final KafkaTemplate<String, String> kafkaTemplateMsg;
    private final WebSocketServer webSocketServer;

    private static final Logger log = LoggerFactory.getLogger(CartItemListener.class);

    public CartItemListener(CartItemService cartItemService, KafkaTemplate<String, Long> kafkaTemplate, KafkaTemplate<String, String> kafkaTemplateMsg, WebSocketServer webSocketServer) {
        this.cartItemService = cartItemService;
        this.kafkaTemplate = kafkaTemplate;
        this.kafkaTemplateMsg = kafkaTemplateMsg;
        this.webSocketServer = webSocketServer;
    }


    @KafkaListener(topics = "checkout", groupId = "topic-order")
    public void addOrderListener(ConsumerRecord<String, Long> record) {
        try {
            cartItemService.checkOutCart(record.value());
            kafkaTemplate.send("order_success", record.value());
            log.info(String.valueOf(record.value()));
            log.info("Get checkout request!!!");
        } catch (Exception e) {
            log.error("Error processing Kafka message: " + e.getMessage(), e);
        }
    }

    @KafkaListener(topics = "order_success", groupId = "topic-order")
    public void addOrderSuccess(ConsumerRecord<String, Long> record) {
        log.info(String.valueOf(record.value()));
        log.info("Send success message!!!");
        webSocketServer.sendMessageToUser(record.value().toString(), "Add order successfully");
    }
}

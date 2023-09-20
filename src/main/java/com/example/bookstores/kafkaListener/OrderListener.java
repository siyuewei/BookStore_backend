package com.example.bookstores.kafkaListener;

import com.example.bookstores.service.OrderService;
import com.example.bookstores.util.request.OrderForm.AddOrderForm;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class OrderListener {
    private final OrderService orderService;

    private final KafkaTemplate<String, String> kafkaTemplate;

    private static final Logger log = LoggerFactory.getLogger(OrderListener.class);

    public OrderListener(OrderService orderService, KafkaTemplate<String, String> kafkaTemplate) {
        this.orderService = orderService;
        this.kafkaTemplate = kafkaTemplate;
    }

    @KafkaListener(topics = "order", groupId = "topic-order")
    public void addOrderListener(ConsumerRecord<String, AddOrderForm> record) {
        try {
            orderService.addOrder(record.value());
//            log.info("addOrderListener: " + record);
//            log.info("addOrderListener: " + record.value());
            kafkaTemplate.send("order_success", "add order success");
        } catch (Exception e) {
            log.error("Error processing Kafka message: " + e.getMessage(), e);
        }
    }

    @KafkaListener(topics = "order_success", groupId = "topic-order")
    public void addOrderSuccess(ConsumerRecord<String, String> record) {
        log.info(record.value());
    }
}

package com.example.bookstores.controller;


import com.example.bookstores.entity.Order;
import com.example.bookstores.service.OrderService;
import com.example.bookstores.util.TestForm;
import com.example.bookstores.util.request.OrderForm.AddOrderForm;
import jakarta.transaction.Transactional;
import org.jetbrains.annotations.NotNull;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
//@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping(value = "api/order")
@Transactional
public class OrderController {
    private final OrderService orderService;
    private final KafkaTemplate<String, AddOrderForm> kafkaTemplate;

    public OrderController(OrderService orderService, KafkaTemplate<String, AddOrderForm> kafkaTemplate, KafkaTemplate<String, TestForm> kafkaTemplate2) {
        this.orderService = orderService;
        this.kafkaTemplate = kafkaTemplate;
    }

    //TODO: remove comment
//    @RequestMapping(value = "/add", method = RequestMethod.POST)
    //    Msg addOrder(@RequestBody @NotNull AddOrderForm addOrderForm) {
//        kafkaTemplate.send("order", addOrderForm);
//        return MsgUtil.makeMsg(MsgUtil.SUCCESS, "Order added successfully");
//    }
////    boolean addOrder(@RequestBody @NotNull AddOrderForm addOrderForm){
////        return orderService.addOrder(addOrderForm);
////    }

//    void addOrder(@RequestBody @NotNull TestForm testForm) {
//        kafkaTemplate2.send("order", testForm);
//    }

    @RequestMapping(value = "/get/{userId}", method = RequestMethod.GET)
    Set<Order> getOrderByUserId(@PathVariable @NotNull Long userId) {
        return orderService.getOrderByUserId(userId);
    }

    @RequestMapping(value = "/delete/{orderId}", method = RequestMethod.DELETE)
    void deleteOrder(@PathVariable @NotNull Long orderId) {
        orderService.deleteOrderById(orderId);
    }

    @RequestMapping(value = "/deleteItem/{orderItemId}", method = RequestMethod.DELETE)
    void deleteOrderItem(@PathVariable @NotNull Long orderItemId) {
        orderService.deleteOrderItemByOrderItemId(orderItemId);
    }

    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    Set<Order> getAllOrder() {
        return orderService.getAllOrder();
    }
}

package com.example.bookstores.controller;


import com.example.bookstores.entity.Order;
import com.example.bookstores.service.OrderService;
import com.example.bookstores.util.request.OrderForm.AddOrderForm;
import jakarta.transaction.Transactional;
import org.jetbrains.annotations.NotNull;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@Transactional
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @RequestMapping(value = "api/order/add",method = RequestMethod.POST)
    boolean addOrder(@RequestBody @NotNull AddOrderForm addOrderForm){
        return orderService.addOrder(addOrderForm);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @RequestMapping(value = "api/order/get/{userId}",method = RequestMethod.GET)
    Set<Order> getOrderByUserId(@PathVariable @NotNull Long userId){
        return orderService.getOrderByUserId(userId);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @RequestMapping(value = "api/order/delete/{orderId}",method = RequestMethod.DELETE)
    void deleteOrder(@PathVariable @NotNull Long orderId){orderService.deleteOrderById(orderId);}

    @CrossOrigin(value = "http://localhost:3000")
    @RequestMapping(value = "api/order/deleteItem/{orderItemId}",method = RequestMethod.DELETE)
    void deleteOrderItem(@PathVariable @NotNull Long orderItemId){orderService.deleteOrderItemByOrderItemId(orderItemId);}

}

package com.example.bookstores.controller;


import com.example.bookstores.entity.Order;
import com.example.bookstores.service.OrderService;
import com.example.bookstores.util.request.OrderForm.AddOrderForm;
import jakarta.transaction.Transactional;
import org.jetbrains.annotations.NotNull;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping(value = "api/order")
@Transactional
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @RequestMapping(value = "/add",method = RequestMethod.POST)
    boolean addOrder(@RequestBody @NotNull AddOrderForm addOrderForm){
        return orderService.addOrder(addOrderForm);
    }

    @RequestMapping(value = "/get/{userId}",method = RequestMethod.GET)
    Set<Order> getOrderByUserId(@PathVariable @NotNull Long userId){
        return orderService.getOrderByUserId(userId);
    }

    @RequestMapping(value = "/delete/{orderId}",method = RequestMethod.DELETE)
    void deleteOrder(@PathVariable @NotNull Long orderId){orderService.deleteOrderById(orderId);}

    @RequestMapping(value = "/deleteItem/{orderItemId}",method = RequestMethod.DELETE)
    void deleteOrderItem(@PathVariable @NotNull Long orderItemId){orderService.deleteOrderItemByOrderItemId(orderItemId);}

    @RequestMapping(value = "/getAll",method = RequestMethod.GET)
    Set<Order> getAllOrder(){return orderService.getAllOrder();}
}

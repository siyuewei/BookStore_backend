package com.example.bookstores.service;

import com.example.bookstores.entity.Order;
import com.example.bookstores.util.request.OrderForm.AddOrderForm;

import java.util.Set;

public interface OrderService {
    boolean addOrder(AddOrderForm addOrderForm);
    Set<Order> getOrderByUserId(Long userId);
    void deleteOrderById(Long orderId);
    void deleteOrderItemByBookId(Long bookId);
    void deleteOrderItemByOrderItemId(Long orderItemId);
    Set<Order> getAllOrder();
}

package com.example.bookstores.dao;

import com.example.bookstores.entity.CartItem;
import com.example.bookstores.entity.Order;
import com.example.bookstores.entity.OrderItem;

import java.util.Set;

public interface OrderDao {
    void saveOrder(Order order);
    void saveOrderItem(OrderItem orderItem);
    Set<Order> getOrderByUserId(Long userId);
    void deleteOrderItemByOrderId(Long orderId);
    void deleteOrderById(Long orderId);
    void deleteOrderItemByBookId(Long bookId);
    void deleteOrderItemByOrderItemId(Long orderItemId);

}

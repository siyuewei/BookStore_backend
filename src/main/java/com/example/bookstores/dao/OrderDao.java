package com.example.bookstores.dao;

import com.example.bookstores.entity.CartItem;
import com.example.bookstores.entity.Order;
import com.example.bookstores.entity.OrderItem;

import javax.xml.crypto.Data;
import java.util.Date;
import java.util.List;
import java.util.Set;

public interface OrderDao {
    void saveOrder(Order order);
    void saveOrderItem(OrderItem orderItem);
    Set<Order> getOrderByUserId(Long userId);
    void deleteOrderItemByOrderId(Long orderId);
    void deleteOrderById(Long orderId);
    void deleteOrderItemByBookId(Long bookId);
    void deleteOrderItemByOrderItemId(Long orderItemId);
    Set<Order> getAllOrder();
    List<Order> getOrdersByTime(Date beginTime, Date endTime);
    List<Order> getOrdersByTimeAndUserId(Date beginTime, Date endTime, Long userId);

    Order getOrderById(Long orderId);

    OrderItem getOrderItemById(Long orderItemId);
}

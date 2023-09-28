package com.example.bookstores.dao;

import com.example.bookstores.entity.Order;

import java.util.Date;
import java.util.List;
import java.util.Set;

public interface OrderDao {
    void saveOrder(Order order);

    Set<Order> getOrderByUserId(Long userId);

    void deleteOrderById(Long orderId);

    Set<Order> getAllOrder();

    List<Order> getOrdersByTime(Date beginTime, Date endTime);

    List<Order> getOrdersByTimeAndUserId(Date beginTime, Date endTime, Long userId);

    Order getOrderById(Long orderId);
}

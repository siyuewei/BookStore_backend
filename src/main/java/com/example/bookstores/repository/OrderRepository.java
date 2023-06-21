package com.example.bookstores.repository;

import com.example.bookstores.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;
import java.util.Set;

public interface OrderRepository extends JpaRepository<Order, Long> {
    Set<Order> getOrderByUserId(Long userId);
    Set<Order> getAllByUserIsTrue();
    List<Order> getOrdersByPurchaseTimeBetween(Date beginTime, Date endTime);
    List<Order> getOrdersByPurchaseTimeBetweenAndUserId(Date beginTime, Date endTime, Long userId);
    Order getOrderById(Long orderId);
}

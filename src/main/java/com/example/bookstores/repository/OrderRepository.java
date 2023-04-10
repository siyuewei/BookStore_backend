package com.example.bookstores.repository;

import com.example.bookstores.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface OrderRepository extends JpaRepository<Order, Long> {
    Set<Order> getOrderByUserId(Long userId);
}

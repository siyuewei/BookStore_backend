package com.example.bookstores.repository;

import com.example.bookstores.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

    void deleteByOrderId(Long orderId);

    void deleteByBookId(Long bookId);

    void deleteOrderItemById(Long orderItemId);

    OrderItem getOrderItemById(Long orderItemId);
}

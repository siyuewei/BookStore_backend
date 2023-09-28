package com.example.bookstores.dao;

import com.example.bookstores.entity.OrderItem;

public interface OrderItemDao {
    void saveOrderItem(OrderItem orderItem);

    void deleteOrderItemByBookId(Long bookId);

    void deleteOrderItemByOrderItemId(Long orderItemId);

    OrderItem getOrderItemById(Long orderItemId);

    public void deleteOrderItemByOrderId(Long orderId);
}

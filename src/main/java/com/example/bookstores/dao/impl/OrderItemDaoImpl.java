package com.example.bookstores.dao.impl;

import com.example.bookstores.dao.OrderItemDao;
import com.example.bookstores.entity.OrderItem;
import com.example.bookstores.repository.OrderItemRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class OrderItemDaoImpl implements OrderItemDao {
    private final OrderItemRepository orderItemRepository;

    public OrderItemDaoImpl(OrderItemRepository orderItemRepository) {
        this.orderItemRepository = orderItemRepository;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, isolation = Isolation.READ_COMMITTED)
    public void saveOrderItem(OrderItem orderItem) {
        orderItemRepository.save(orderItem);
    }

    @Override
    public void deleteOrderItemByOrderId(Long orderId) {
        orderItemRepository.deleteByOrderId(orderId);
    }

    @Override
    public void deleteOrderItemByBookId(Long bookId) {
        orderItemRepository.deleteByBookId(bookId);
    }

    @Override
    public void deleteOrderItemByOrderItemId(Long orderItemId) {
        orderItemRepository.deleteOrderItemById(orderItemId);
    }

    @Override
    public OrderItem getOrderItemById(Long orderItemId) {
        return orderItemRepository.getOrderItemById(orderItemId);
    }
}

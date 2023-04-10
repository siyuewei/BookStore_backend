package com.example.bookstores.dao.impl;

import com.example.bookstores.dao.OrderDao;
import com.example.bookstores.entity.Order;
import com.example.bookstores.entity.OrderItem;
import com.example.bookstores.repository.OrderItemRepository;
import com.example.bookstores.repository.OrderRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public class OrderDaoImpl implements OrderDao {
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;

    public OrderDaoImpl(OrderRepository orderRepository, OrderItemRepository orderItemRepository) {
        this.orderRepository = orderRepository;
        this.orderItemRepository = orderItemRepository;
    }


    @Override
    public void saveOrder(Order order) {
        orderRepository.save(order);
    }

    @Override
    public void saveOrderItem(OrderItem orderItem) {
        orderItemRepository.save(orderItem);
    }

    @Override
    public Set<Order> getOrderByUserId(Long userId) {
        return orderRepository.getOrderByUserId(userId);
    }

    @Override
    public void deleteOrderItemByOrderId(Long orderId) {
        orderItemRepository.deleteByOrderId(orderId);
    }

    @Override
    public void deleteOrderById(Long orderId) {
        orderRepository.deleteById(orderId);
    }

    @Override
    public void deleteOrderItemByBookId(Long bookId) {
        orderItemRepository.deleteByBookId(bookId);
    }

    @Override
    public void deleteOrderItemByOrderItemId(Long orderItemId) {
        orderItemRepository.deleteOrderItemById(orderItemId);
    }
}

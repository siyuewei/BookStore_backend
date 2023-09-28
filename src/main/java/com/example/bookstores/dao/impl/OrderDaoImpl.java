package com.example.bookstores.dao.impl;

import com.example.bookstores.dao.OrderDao;
import com.example.bookstores.entity.Order;
import com.example.bookstores.repository.OrderRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Set;

@Repository
public class OrderDaoImpl implements OrderDao {
    private final OrderRepository orderRepository;

    public OrderDaoImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }


    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, isolation = Isolation.READ_COMMITTED)
    public void saveOrder(Order order) {
        orderRepository.save(order);
    }


    @Override
    public Set<Order> getOrderByUserId(Long userId) {
        return orderRepository.getOrderByUserId(userId);
    }


    @Override
    public void deleteOrderById(Long orderId) {
        orderRepository.deleteById(orderId);
    }


    @Override
    public Set<Order> getAllOrder() {
        return orderRepository.getAllByUserIsTrue();
    }

    @Override
    public List<Order> getOrdersByTime(Date beginTime, Date endTime) {
        return orderRepository.getOrdersByPurchaseTimeBetween(beginTime, endTime);
    }

    @Override
    public List<Order> getOrdersByTimeAndUserId(Date beginTime, Date endTime, Long userId) {
        return orderRepository.getOrdersByPurchaseTimeBetweenAndUserId(beginTime, endTime, userId);
    }

    @Override
    public Order getOrderById(Long orderId) {
        return orderRepository.getOrderById(orderId);
    }

}

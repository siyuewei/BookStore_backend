package com.example.bookstores.service.Impl;

import com.example.bookstores.dao.OrderDao;
import com.example.bookstores.dao.impl.BookDaoImpl;
import com.example.bookstores.dao.impl.UserDaoImpl;
import com.example.bookstores.entity.Book;
import com.example.bookstores.entity.Order;
import com.example.bookstores.entity.OrderItem;
import com.example.bookstores.entity.User;
import com.example.bookstores.service.OrderService;
import com.example.bookstores.util.request.OrderForm.AddOrderForm;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class OrderServiceImpl implements OrderService {
    private final OrderDao orderDao ;
    private final UserDaoImpl userDao;
    private final BookDaoImpl bookDao;

    public OrderServiceImpl(OrderDao orderDao, UserDaoImpl userDao, BookDaoImpl bookDao) {
        this.orderDao = orderDao;
        this.userDao = userDao;
        this.bookDao = bookDao;
    }

    @Override
    public boolean addOrder(AddOrderForm addOrderForm) {
        User user = userDao.getUserById(addOrderForm.getUserId());
        Order order = new Order(addOrderForm.getTotalPrice(),addOrderForm.getPurchaseTime(),user);
        Pair<Long,Integer>[] bookIdAndAmounts = addOrderForm.getBookIdAndAmounts();
        List<OrderItem> orderItems = new ArrayList<>();
        for (Pair<Long,Integer> bookIdAndAmount : bookIdAndAmounts) {
            Book book = bookDao.getBookById(bookIdAndAmount.getFirst());
            OrderItem orderItem = new OrderItem(bookIdAndAmount.getSecond(),book,order);
            orderItems.add(orderItem);
        }
        order.setOrderItems(orderItems);
        orderDao.saveOrder(order);
        for (OrderItem orderItem : orderItems) {
            orderDao.saveOrderItem(orderItem);
        }
        return true;
    }

    @Override
    public Set<Order> getOrderByUserId(Long userId) {
        return orderDao.getOrderByUserId(userId);
    }

    @Override
    public void deleteOrderById(Long orderId) {
        orderDao.deleteOrderById(orderId);
        orderDao.deleteOrderItemByOrderId(orderId);
    }

    @Override
    public void deleteOrderItemByBookId(Long bookId) {
        orderDao.deleteOrderItemByBookId(bookId);
    }

    @Override
    public void deleteOrderItemByOrderItemId(Long orderItemId) {
        orderDao.deleteOrderItemByOrderItemId(orderItemId);
    }
}

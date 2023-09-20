package com.example.bookstores.serviceImpl;

import com.example.bookstores.dao.BookDao;
import com.example.bookstores.dao.OrderDao;
import com.example.bookstores.dao.UserDao;
import com.example.bookstores.entity.Book;
import com.example.bookstores.entity.Order;
import com.example.bookstores.entity.OrderItem;
import com.example.bookstores.entity.User;
import com.example.bookstores.service.OrderService;
import com.example.bookstores.util.request.OrderForm.AddOrderForm;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class OrderServiceImpl implements OrderService {
    private final OrderDao orderDao;
    private final UserDao userDao;
    private final BookDao bookDao;

    public OrderServiceImpl(OrderDao orderDao, UserDao userDao, BookDao bookDao) {
        this.orderDao = orderDao;
        this.userDao = userDao;
        this.bookDao = bookDao;
    }

    @Override
    public boolean addOrder(AddOrderForm addOrderForm) {
        User user = userDao.getUserById(addOrderForm.getUserId());
        Order order = new Order(addOrderForm.getTotalPrice(), addOrderForm.getPurchaseTime(), user);
        List<Map<Long, Integer>> bookIdAndAmounts = addOrderForm.getBookIdAndAmounts();
        List<OrderItem> orderItems = new ArrayList<>();
        for (Map<Long, Integer> bookIdAndAmount : bookIdAndAmounts) {
            for (Map.Entry<Long, Integer> entry : bookIdAndAmount.entrySet()) {
                Long bookId = entry.getKey();
                Integer amount = entry.getValue();

                // 使用 bookId 获取 Book 对象
                Book book = bookDao.getBookById(bookId);

                // 创建 OrderItem 对象并添加到 orderItems 列表
                OrderItem orderItem = new OrderItem(amount, book, order);
                orderItems.add(orderItem);
            }
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
//        return orderDao.getOrderByUserId(userId);
        Set<Order> orders = orderDao.getOrderByUserId(userId);
        orders.removeIf(Order::getIsDelete);

        Comparator<Order> orderComparator = Comparator.comparing(Order::getPurchaseTime).reversed();
        TreeSet<Order> sortedOrders = new TreeSet<>(orderComparator);
        sortedOrders.addAll(orders);

        return sortedOrders;
    }

    @Override
    public void deleteOrderById(Long orderId) {
        Order order = orderDao.getOrderById(orderId);
        order.setIsDelete(true);
        orderDao.saveOrder(order);
    }

    @Override
    public void deleteOrderItemByBookId(Long bookId) {
        orderDao.deleteOrderItemByBookId(bookId);
    }

    @Override
    public void deleteOrderItemByOrderItemId(Long orderItemId) {
        OrderItem orderItem = orderDao.getOrderItemById(orderItemId);
        orderItem.setIsDelete(true);
        orderDao.saveOrderItem(orderItem);
    }

    @Override
    public Set<Order> getAllOrder() {
        return orderDao.getAllOrder();
    }
}

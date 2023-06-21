package com.example.bookstores.service.Impl;

import com.example.bookstores.dao.BookDao;
import com.example.bookstores.dao.CartItemDao;
import com.example.bookstores.dao.OrderDao;
import com.example.bookstores.dao.UserDao;
import com.example.bookstores.entity.*;
import com.example.bookstores.service.CartItemService;
import com.example.bookstores.util.msg.Msg;
import com.example.bookstores.util.msg.MsgUtil;
import com.example.bookstores.util.request.CartForm.AddCartItemForm;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Optional;
import java.util.Set;

@Service
public class CartItemItemServiceImpl implements CartItemService {
    private final CartItemDao cartItemDao;
    private final BookDao bookDao;
    private final UserDao UserDao;
    private final OrderDao orderDao;

    public CartItemItemServiceImpl(CartItemDao cartItemDao, BookDao bookDao, UserDao UserDao, OrderDao orderDao) {
        this.cartItemDao = cartItemDao;
        this.bookDao = bookDao;
        this.UserDao = UserDao;
        this.orderDao = orderDao;
    }

    @Override
    public boolean addCartItem(AddCartItemForm addCartItemForm) {
        Long bookId = addCartItemForm.getBookId();
        Long userId = addCartItemForm.getUserId();
        if (cartItemDao.findCartItemByUserIdAndBookId(userId,bookId).isPresent()) {
            cartItemDao.addAmountByUserIdAndBookId(addCartItemForm.getUserId(), bookId);
            return false;
        }
        else {
            Book book = bookDao.getBookById(bookId);
            if(book == null) {
                System.out.println("book is null");
                return false;
            }else {
                User user = UserDao.getUserById(addCartItemForm.getUserId());
                cartItemDao.save(new CartItem(book, user, addCartItemForm.getAmount()));
                return true;
            }

        }
    }

    @Override
    public Set<CartItem> getCartItemByUserId(Long userId) {
        return cartItemDao.getCartItemByUserId(userId);
    }


    @Override
    public void changeCartItem(AddCartItemForm addCartItemForm) {
        Long bookId = addCartItemForm.getBookId();
        Long userId = addCartItemForm.getUserId();
        Optional<CartItem> optionalCart = cartItemDao.findCartItemByUserIdAndBookId(userId,bookId);
        if (optionalCart.isPresent()) {
            CartItem cartItem = optionalCart.get();
            cartItem.setAmount(addCartItemForm.getAmount());
            cartItemDao.save(cartItem);
        } else {
            if (addCartItemForm.getBookId() == null || addCartItemForm.getUserId() == null || addCartItemForm.getAmount() == null) {
                System.out.println("addCartForm is null");
            } else {
                Book book = bookDao.getBookById(addCartItemForm.getBookId());
                User user = UserDao.getUserById(addCartItemForm.getUserId());
                CartItem cartItem = new CartItem(book,user, addCartItemForm.getAmount());
                cartItemDao.save(cartItem);
            }
        }
    }

    @Override
    public void deleteCartItemByUserIdBookId(Long userId, Long bookId) {
        cartItemDao.deleteCartByUserIdBookId(userId,bookId);
    }

    @Override
    public Msg checkOutCart(Long userId) {
//        LocalDate localDate = LocalDate.now();
//        Instant instant = localDate.atTime(LocalTime.MIDNIGHT).atZone(ZoneId.systemDefault()).toInstant();
//        Date purchaseDate = Date.from(instant);



        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
        Date purchaseDate = new Date(System.currentTimeMillis());

        Double totalPrice = 0.0;
        User user = UserDao.getUserById(userId);

        Set<CartItem> cartItems = cartItemDao.getCartItemByUserId(userId);
        for(CartItem cartItem : cartItems){
            Book book = bookDao.getBookById(cartItem.getBook().getId());
            if(book.getIsDelete()){
                return MsgUtil.makeMsg(MsgUtil.ERROR,book.getName() + " 已下架",null);
            }
            if(book.getInventory() < cartItem.getAmount()) {
                return MsgUtil.makeMsg(MsgUtil.ERROR,book.getName() + " 库存不足",null);
            }
        }


        if(cartItems.isEmpty()) {
            return MsgUtil.makeMsg(MsgUtil.ERROR,"购物车为空",null);
        }

        for(CartItem cartItem : cartItems) {
            totalPrice += cartItem.getAmount() * cartItem.getBook().getPrice();
        }
        Order order = new Order(totalPrice,purchaseDate,user);
        orderDao.saveOrder(order);

        for(CartItem cartItem : cartItems) {
            OrderItem orderItem = new OrderItem(cartItem.getAmount(),cartItem.getBook(),cartItem.getAmount() * cartItem.getBook().getPrice(),order);
            orderDao.saveOrderItem(orderItem);

            Book book = bookDao.getBookById(cartItem.getBook().getId());
            book.setInventory(book.getInventory() - cartItem.getAmount());
            book.setSales(book.getSales() + cartItem.getAmount());
            bookDao.save(book);

            cartItemDao.deleteCartByUserIdBookId(userId,cartItem.getBook().getId());
        }

        return MsgUtil.makeMsg(MsgUtil.SUCCESS,"购买成功",null);
    }

}

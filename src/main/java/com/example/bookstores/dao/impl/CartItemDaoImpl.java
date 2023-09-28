package com.example.bookstores.dao.impl;

import com.example.bookstores.dao.CartItemDao;
import com.example.bookstores.entity.CartItem;
import com.example.bookstores.repository.CartItemRepository;
import com.example.bookstores.repository.UserRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.Set;

@Repository
public class CartItemDaoImpl implements CartItemDao {
    private final CartItemRepository cartItemRepository;

    public CartItemDaoImpl(CartItemRepository cartItemRepository, UserRepository userRepository) {
        this.cartItemRepository = cartItemRepository;
    }

    @Override
    public Optional<CartItem> findCartByBookId(Long bookId) {
        return cartItemRepository.findCartByBookId(bookId);
    }


    @Override
    public Optional<CartItem> findCartItemByUserIdAndBookId(Long userId, Long bookId) {
        return cartItemRepository.findCartItemByUserIdAndBookId(userId, bookId);
    }

    @Override
    public void save(CartItem cartItem) {
        cartItemRepository.save(cartItem);
    }

    @Override
    public void addAmountByUserIdAndBookId(Long userId, Long bookId) {
        CartItem cartItem = cartItemRepository.getCartByUserIdAndBookId(userId, bookId);
        cartItem.setAmount(cartItem.getAmount() + 1);
        cartItemRepository.save(cartItem);
    }

    @Override
    public Set<CartItem> getCartItemByUserId(Long userId) {
//        System.out.println("getCartItemByUserId");
        return cartItemRepository.getAllByUserId(userId);
//        User user = userRepository.getUserById(userId);
//        return cartItemRepository.getAllByUser(user);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, isolation = Isolation.READ_COMMITTED)
    public void deleteCartByUserIdBookId(Long userId, Long bookId) {
        cartItemRepository.deleteCartByUserIdAndBookId(userId, bookId);
    }

}

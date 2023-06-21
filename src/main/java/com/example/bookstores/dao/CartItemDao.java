package com.example.bookstores.dao;

import com.example.bookstores.entity.CartItem;
import com.example.bookstores.entity.User;

import java.util.Optional;
import java.util.Set;

public interface CartItemDao {
    Optional<CartItem> findCartByBookId(Long id);
    Set<CartItem> getCartItemByUserId(Long userId);

    void save(CartItem cartItem);

    void addAmountByUserIdAndBookId(Long userId, Long bookId);

    Optional<CartItem> findCartItemByUserIdAndBookId(Long userId,Long bookId);

    void deleteCartByUserIdBookId(Long userId, Long bookId);
}

package com.example.bookstores.repository;

import com.example.bookstores.entity.CartItem;
import com.example.bookstores.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.Set;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    Optional<CartItem> findCartByBookId(Long bookId);

    Optional<CartItem> findCartItemByUserIdAndBookId(Long userId,Long bookId);

    Set<CartItem> getAllByUserId(Long userId);

    Set<CartItem> getAllByUser(User user);

    void deleteCartByUserIdAndBookId(Long userId, Long bookId);

    CartItem getCartByUserIdAndBookId(Long userId, Long bookId);
}

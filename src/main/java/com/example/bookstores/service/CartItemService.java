package com.example.bookstores.service;

import com.example.bookstores.entity.CartItem;
import com.example.bookstores.util.msg.Msg;
import com.example.bookstores.util.request.CartForm.AddCartItemForm;

import java.util.Set;

public interface CartItemService {
    boolean addCartItem(AddCartItemForm addCartItemForm);

    Set<CartItem> getCartItemByUserId(Long userId);

    void changeCartItem(AddCartItemForm addCartItemForm);

    void deleteCartItemByUserIdBookId(Long userId, Long bookId);

    Msg checkOutCart(Long userId);
}

package com.example.bookstores.controller;


import com.example.bookstores.entity.CartItem;
import com.example.bookstores.service.CartItemService;
import com.example.bookstores.util.msg.Msg;
import com.example.bookstores.util.request.CartForm.AddCartItemForm;
import jakarta.transaction.Transactional;
import jakarta.websocket.server.PathParam;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping(value = "api/cart")
@Transactional
public class CartItemController {
    private final CartItemService cartItemService;

    public CartItemController(CartItemService cartItemService) {
        this.cartItemService = cartItemService;
    }

    @RequestMapping(value = "/add",method = RequestMethod.POST)
    boolean addCartItem(@RequestBody @NotNull AddCartItemForm addCartItemForm){
        return cartItemService.addCartItem(addCartItemForm);
    }

    @RequestMapping(value = "/change",method = RequestMethod.POST)
    void changeCartItem(@RequestBody @NotNull AddCartItemForm addCartItemForm){
        cartItemService.changeCartItem(addCartItemForm);
    }

    @RequestMapping(value = "/get/{userId}",method = RequestMethod.GET)
    Set<CartItem> getCartItemByUserId(@PathVariable Long userId){
        return cartItemService.getCartItemByUserId(userId);
    }

    @RequestMapping(value = "/delete",method = RequestMethod.DELETE)
    void deleteCartItemByBookId(@PathParam("userId") Long userId,@PathParam("bookId") Long bookId){
        cartItemService.deleteCartItemByUserIdBookId(userId,bookId);
    }

    @RequestMapping(value = "/checkout/{userId}",method = RequestMethod.DELETE)
    Msg checkOutCart(@PathVariable("userId") Long userId){
        return cartItemService.checkOutCart(userId);
    }
}


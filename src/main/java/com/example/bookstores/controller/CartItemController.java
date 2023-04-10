package com.example.bookstores.controller;


import com.example.bookstores.entity.CartItem;
import com.example.bookstores.service.CartItemService;
import com.example.bookstores.util.request.CartForm.AddCartItemForm;
import jakarta.transaction.Transactional;
import jakarta.websocket.server.PathParam;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@Transactional
public class CartItemController {
    private final CartItemService cartItemService;

    public CartItemController(CartItemService cartItemService) {
        this.cartItemService = cartItemService;
    }


    @CrossOrigin(origins = "http://localhost:3000")
    @RequestMapping(value = "api/cart/add",method = RequestMethod.POST)
    boolean addCartItem(@RequestBody @NotNull AddCartItemForm addCartItemForm){
        return cartItemService.addCartItem(addCartItemForm);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @RequestMapping(value = "api/cart/change",method = RequestMethod.POST)
    void changeCartItem(@RequestBody @NotNull AddCartItemForm addCartItemForm){
        cartItemService.changeCartItem(addCartItemForm);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @RequestMapping(value = "api/cart/get/{userId}",method = RequestMethod.GET)
    Set<CartItem> getCartItemByUserId(@PathVariable Long userId){
        return cartItemService.getCartItemByUserId(userId);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @RequestMapping(value = "api/cart/delete",method = RequestMethod.DELETE)
    void deleteCartItemByBookId(@PathParam("userId") Long userId,@PathParam("bookId") Long bookId){
        cartItemService.deleteCartItemByUserIdBookId(userId,bookId);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @RequestMapping(value = "api/cart/checkout/{userId}",method = RequestMethod.DELETE)
    void checkOutCart(@PathVariable("userId") Long userId){
        cartItemService.checkOutCart(userId);
    }
}

package com.example.bookstores.util.request.CartForm;

import com.example.bookstores.entity.Book;

public class GetCartItemForm {
    private Book book;
    private Long userId;
    private Integer amount;
    private Long id;

    public void setBook(Book book) {
        this.book = book;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Book getBook() {
        return book;
    }

    public Long getUserId() {
        return userId;
    }

    public Integer getAmount() {
        return amount;
    }

    public Long getId() {
        return id;
    }
}

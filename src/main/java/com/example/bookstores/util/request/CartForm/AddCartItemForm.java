package com.example.bookstores.util.request.CartForm;

public class AddCartItemForm {
    private Long bookId;
    private Long userId;
    private Integer amount;

    public Long getBookId() {
        return bookId;
    }

    public Long getUserId() {
        return userId;
    }

    public Integer getAmount() {
        return amount;
    }
}

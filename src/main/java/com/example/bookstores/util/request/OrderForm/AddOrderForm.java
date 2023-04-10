package com.example.bookstores.util.request.OrderForm;

import org.springframework.data.util.Pair;

import java.security.Timestamp;
import java.util.Date;

public class AddOrderForm {
    private Long userId;
    private Pair<Long,Integer>[] bookIdAndAmounts;
    private Date purchaseTime;
    private Double totalPrice;

    public Long getUserId() {
        return userId;
    }

    public Pair<Long, Integer>[] getBookIdAndAmounts() {
        return bookIdAndAmounts;
    }

    public Date getPurchaseTime() {
        return purchaseTime;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setBookIdAndAmounts(Pair<Long, Integer>[] bookIdAndAmounts) {
        this.bookIdAndAmounts = bookIdAndAmounts;
    }

    public void setPurchaserTime(Date purchaseTime) {
        this.purchaseTime = purchaseTime;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }
}

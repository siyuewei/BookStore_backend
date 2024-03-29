package com.example.bookstores.util.request.OrderForm;

import java.util.Date;
import java.util.List;
import java.util.Map;

public class AddOrderForm {
    private Long userId;
    private List<Map<Long, Integer>> bookIdAndAmounts;
    private Date purchaseTime;
    private Double totalPrice;

    public Long getUserId() {
        return userId;
    }

    public List<Map<Long, Integer>> getBookIdAndAmounts() {
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

    public void setBookIdAndAmounts(List<Map<Long, Integer>> bookIdAndAmounts) {
        this.bookIdAndAmounts = bookIdAndAmounts;
    }

    public void setPurchaserTime(Date purchaseTime) {
        this.purchaseTime = purchaseTime;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    //添加toString方法
    @Override
    public String toString() {
        return "AddOrderForm [userId=" +
                userId +
                ", bookIdAndAmounts=" +
                bookIdAndAmounts +
                ", purchaseTime=" +
                purchaseTime +
                ", totalPrice=" +
                totalPrice +
                "]";
    }

}

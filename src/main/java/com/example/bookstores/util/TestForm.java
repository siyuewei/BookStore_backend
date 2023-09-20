package com.example.bookstores.util;


import java.util.Date;

public class TestForm {
    private Long userId;
    private Date purchaseTime;
    private Double totalPrice;

    public Long getUserId() {
        return userId;
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
                ", purchaseTime=" +
                purchaseTime +
                ", totalPrice=" +
                totalPrice +
                "]";
    }

}

package com.example.bookstores.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.security.Timestamp;
import java.util.Date;
import java.util.List;

@Setter
@Getter
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "total_price", length = 16, nullable = false)
    private Double totalPrice;

    @Column(name = "purchase_time", length = 32, nullable = false)
    private Date purchaseTime;

    @OneToMany(mappedBy = "order",cascade=CascadeType.ALL)
    private List<OrderItem> orderItems;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    @JsonIgnore
    private User user;

    public Order() {
    }

    public Order(Double totalPrice, Date purchaseTime, User user) {
        this.totalPrice = totalPrice;
        this.purchaseTime = purchaseTime;
        this.user = user;
    }

}

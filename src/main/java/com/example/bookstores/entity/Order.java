package com.example.bookstores.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
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

    @Column(name = "is_delete", nullable = false)
    private Boolean isDelete = false;

    @Column(name = "purchase_time", length = 32, nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date purchaseTime;

    @OneToMany(mappedBy = "order",cascade=CascadeType.ALL)
    private List<OrderItem> orderItems;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    private User user;

    public Order() {
    }

    public Order(Double totalPrice, Date purchaseTime, User user) {
        this.totalPrice = totalPrice;
        this.purchaseTime = purchaseTime;
        this.user = user;
    }

}

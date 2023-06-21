package com.example.bookstores.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
@Entity
@Table(name = "order_item")
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "amount", length = 16, nullable = false)
    private Integer amount;

    @Column(name = "price", length = 16, nullable = false)
    private Double price;

    @Column(name = "is_delete", nullable = false)
    private Boolean isDelete = false;

    @ManyToOne
    @JoinColumn(name = "book_id", referencedColumnName = "id", nullable = false)
    private Book book;

    @ManyToOne
    @JoinColumn(name = "order_id", referencedColumnName = "id", nullable = false)
    @JsonIgnore
    private Order order;

    public OrderItem() {
    }

    public OrderItem(Integer amount, Book book, Double price, Order order) {
        this.amount = amount;
        this.book = book;
        this.price = price;
        this.order = order;
    }

    public OrderItem(Integer amount, Book book, Order order) {
        this.amount = amount;
        this.book = book;
        this.price = book.getPrice()*amount;
        this.order = order;
    }
}

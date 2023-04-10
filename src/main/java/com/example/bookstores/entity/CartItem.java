package com.example.bookstores.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
@Entity
@Table(name = "cart_item")
public class CartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "amount", length = 16, nullable = false)
    private Integer amount;
//    @Column(name = "book_id", length = 16, nullable = false)
//    private Long bookId;
//    @Column(name = "user_id", length = 16, nullable = false)
//    private Long userId;

    @OneToOne
    @JoinColumn(name = "book_id", referencedColumnName = "id", nullable = false)
    private Book book;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @JsonIgnore
    private User user;

    public CartItem(Book book, User user, Integer amount) {
        this.amount = amount;
        this.book = book;
        this.user = user;
    }

    public CartItem() {
    }
}

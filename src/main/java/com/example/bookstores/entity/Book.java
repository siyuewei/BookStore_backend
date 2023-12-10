package com.example.bookstores.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@Entity
@Table(name = "book")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name", length = 128, nullable = false)
    private String name;
    @Column(name = "isbn", length = 32, nullable = false, unique = true)
    private String isbn;
    @Column(name = "author", length = 64, nullable = false)
    private String author;
    @Column(name = "price", length = 8, nullable = false)
    private Double price;
    @Column(name = "description", length = 2048)
    private String description;
    @Column(name = "inventory", length = 8, nullable = false)
    private Integer inventory;
    @Column(name = "sales", length = 8, nullable = false)
    private Integer sales;
    @Column(name = "isDelete", length = 8, nullable = false)
    private Boolean isDelete;
    @Column(name = "image", length = 2048)
    private String image;
    @Column(name = "tag", length = 2048)
    private String tag;


    public Book(String name, String description, String author, String ISBN, Double price, Integer inventory,String image, String tag) {
        this.name = name;
        this.author = author;
        this.description = description;
        this.isbn = ISBN;
        this.price = price;
        this.inventory = inventory;
        this.image = image;
        this.tag = tag;
    }

    public Book() {
    }
}

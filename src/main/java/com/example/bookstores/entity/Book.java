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
    @Column(name = "image", length = 16384)
    private String image;

    @JsonIgnoreProperties(value = {"books"})
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "book_tag",
            inverseJoinColumns = @JoinColumn(name = "tag_id", referencedColumnName = "id"),
            joinColumns = @JoinColumn(name = "book_id", referencedColumnName = "id")
    )
    private List<Tag> tags;

    public Book(String name, String image, String description, String author, String ISBN, Double price, Integer inventory, List<Tag> tags) {
        this.name = name;
        this.image = image;
        this.author = author;
        this.description = description;
        this.isbn = ISBN;
        this.price = price;
        this.inventory = inventory;
        this.tags = tags;
    }

    public Book() {
    }
}

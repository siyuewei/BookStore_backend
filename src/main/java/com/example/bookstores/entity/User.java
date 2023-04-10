package com.example.bookstores.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "username", length = 128, nullable = false, unique = true)
    private String username;
    @Column(name = "name", length = 128)
    private String name;
    @Column(name = "email", length = 128, unique = true)
    private String email;
    @Column(name = "avatar", length = 10240)
    private String avatar;
    @Column(name = "notes", length = 10240)
    private String notes;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    @JsonIgnore
    private UserAuth userAuth;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<CartItem> cartItems;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Order> orders;


    public User(String username,String name,String email, String avatar,String notes) {
        this.username = username;
        this.name = name;
        this.email = email;
        this.notes = notes;
        this.avatar = avatar;
    }

    public User(String username) {
        this.username = username;
    }

    public User() {
    }
}

package com.example.bookstores.service;

import com.example.bookstores.entity.Book;

import java.util.List;

public interface BookService {
    Book getBookById(Long id);

    Book getBookByAuthor(String author);

    void addBook(String title, String image, String desc, String author, String isbn, Double price, Integer inventory, String tagName);

    List<Book> getBooks();
}

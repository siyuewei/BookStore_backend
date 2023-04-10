package com.example.bookstores.dao;

import com.example.bookstores.entity.Book;

import java.util.List;
import java.util.Optional;

public interface BookDao {
    Optional<Book> findBookById(Long id);

    Book findBookByAuthor(String author);

    void addBook(Book book);

    Book getBookById(Long id);

    List<Book> getBooks();
}

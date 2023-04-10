package com.example.bookstores.repository;

import com.example.bookstores.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long> {
    Optional<Book> findByName(String name);
    Book getByName(String name);
    Book findBookByAuthor(String author);

//    @Query(value = "select * from book where author = :author", nativeQuery = true)
    Book getBookById(Long id);
}

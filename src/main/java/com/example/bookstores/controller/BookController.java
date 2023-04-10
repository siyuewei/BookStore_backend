package com.example.bookstores.controller;

import com.example.bookstores.entity.Book;
import com.example.bookstores.service.BookService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Transactional
public class BookController {
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }


    @RequestMapping(value = "api/book/{id}",method = RequestMethod.GET)
    @CrossOrigin
    Book getBookById(@PathVariable Long id){
        return bookService.getBookById(id);
    }

    @RequestMapping(value = "api/book/author/{author}",method = RequestMethod.GET)
    Book getBookByAuthor(@PathVariable String author){
        return bookService.getBookByAuthor(author);
    }

    @RequestMapping(value = "api/book/add",method = RequestMethod.POST)
    void addBook(String title, String image, String desc, String author, String isbn, Double price, Integer inventory, String tagName){
        bookService.addBook(title, image, desc, author, isbn, price, inventory, tagName);
    }

    @CrossOrigin
    @RequestMapping(value = "api/book/get",method = RequestMethod.GET)
    List<Book> getBookByUserId(){
        return bookService.getBooks();
    }

}

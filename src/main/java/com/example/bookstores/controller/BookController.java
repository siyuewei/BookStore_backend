package com.example.bookstores.controller;

import com.example.bookstores.entity.Book;
import com.example.bookstores.service.BookService;
import com.example.bookstores.util.msg.Msg;
import com.example.bookstores.util.request.BookForm.BookAmountPrice;
import com.example.bookstores.util.request.BookForm.GerUserStatisticsForm;
import com.example.bookstores.util.request.BookForm.GetUserBookForm;
import jakarta.transaction.Transactional;
import jakarta.websocket.server.PathParam;
import org.jetbrains.annotations.NotNull;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
//@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping(value = "api/book")
@Transactional
public class BookController {
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    Book getBookById(@PathVariable Long id){
        return bookService.getBookById(id);
    }

    @RequestMapping(value = "/add",method = RequestMethod.POST)
    Msg addBook(@RequestBody Book book){
        return bookService.addBook(book);
    }

    @RequestMapping(value = "/get",method = RequestMethod.GET)
    List<Book> getAllBooks(){
        return bookService.getBooks();
    }

    @RequestMapping(value = "/update",method = RequestMethod.POST)
    Msg updateBook(@RequestBody @NotNull Book book){
        return bookService.updateBook(book);
    }

    @RequestMapping(value = "/delete/{id}",method = RequestMethod.DELETE)
    void deleteBook(@PathVariable Long id){
        bookService.deleteBook(id);
    }


    @RequestMapping(value = "/get/statistics/byBook",method = RequestMethod.GET)
    List<BookAmountPrice> getBookStatisticsByBook(
            @PathParam("beginTime") @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss") Date beginTime,
            @PathParam("endTime") @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss") Date endTime){
        return bookService.getBookStatistics(beginTime,endTime);
    }

    @RequestMapping(value = "/get/statistics/byUser",method = RequestMethod.GET)
    List<GerUserStatisticsForm> getBookStatisticsByUser(
            @PathParam("beginTime") @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss") Date beginTime,
            @PathParam("endTime") @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss") Date endTime){
        return bookService.getUserStatistics(beginTime,endTime);
    }

    @RequestMapping(value = "/get/statistics/{userId}",method = RequestMethod.GET)
    GetUserBookForm getUserBookForms(
            @PathParam("beginTime") @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss") Date beginTime,
            @PathParam("endTime") @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss") Date endTime,
            @PathVariable Long userId){
        return bookService.getUserBookForms(beginTime,endTime,userId);
    }
}

package com.example.bookstores.controller;

import com.example.bookstores.entity.Book;
import com.example.bookstores.entity.BookTag;
import com.example.bookstores.repository.BookTagRepository;
import com.example.bookstores.service.BookService;
import com.example.bookstores.util.msg.Msg;
import com.example.bookstores.util.request.BookForm.BookAmountPrice;
import com.example.bookstores.util.request.BookForm.GerUserStatisticsForm;
import com.example.bookstores.util.request.BookForm.GetUserBookForm;
import jakarta.transaction.Transactional;
import jakarta.websocket.server.PathParam;
import org.jetbrains.annotations.NotNull;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
//@CrossOrigin(origins = "http://localhost:3000")
//@RequestMapping(value = "api/book")
@Transactional
public class BookController {
    private final BookService bookService;
    private final BookTagRepository bookTagRepository;


    public BookController(BookService bookService, BookTagRepository bookTagRepository) {
        this.bookService = bookService;
        this.bookTagRepository = bookTagRepository;
    }

    @RequestMapping(value = "api/book/{id}",method = RequestMethod.GET)
    Book getBookById(@PathVariable Long id){
        return bookService.getBookById(id);
    }

    @RequestMapping(value = "api/book/add",method = RequestMethod.POST)
    Msg addBook(@RequestBody Book book){
        return bookService.addBook(book);
    }

    @RequestMapping(value = "api/book/get",method = RequestMethod.GET)
    List<Book> getAllBooks(){
        return bookService.getBooks();
    }

    @RequestMapping(value = "api/book/update",method = RequestMethod.POST)
    Msg updateBook(@RequestBody @NotNull Book book){
        return bookService.updateBook(book);
    }

    @RequestMapping(value = "api/book/delete/{id}",method = RequestMethod.DELETE)
    void deleteBook(@PathVariable Long id){
        bookService.deleteBook(id);
    }


    @RequestMapping(value = "api/book/get/statistics/byBook",method = RequestMethod.GET)
    List<BookAmountPrice> getBookStatisticsByBook(
            @PathParam("beginTime") @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss") Date beginTime,
            @PathParam("endTime") @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss") Date endTime){
        return bookService.getBookStatistics(beginTime,endTime);
    }

    @RequestMapping(value = "api/book/get/statistics/byUser",method = RequestMethod.GET)
    List<GerUserStatisticsForm> getBookStatisticsByUser(
            @PathParam("beginTime") @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss") Date beginTime,
            @PathParam("endTime") @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss") Date endTime){
        return bookService.getUserStatistics(beginTime,endTime);
    }

    @RequestMapping(value = "api/book/get/statistics/{userId}",method = RequestMethod.GET)
    GetUserBookForm getUserBookForms(
            @PathParam("beginTime") @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss") Date beginTime,
            @PathParam("endTime") @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss") Date endTime,
            @PathVariable Long userId){
        return bookService.getUserBookForms(beginTime,endTime,userId);
    }

    @RequestMapping(value = "api/book/searchByTag", method = RequestMethod.GET)
    public List<Book> searchBooksByTag(@PathParam("tag") String tag) {
        return bookService.searchBooksByTag(tag);
    }

    @RequestMapping(value = "api/book/neo4j", method = RequestMethod.GET)
    public List<Book> testNeo4j() {
        bookTagRepository.deleteAll();

        BookTag booktag1 = new BookTag();
        booktag1.setTag("经典文学");
        BookTag booktag2 = new BookTag();
        booktag2.setTag("社会与政治");
        BookTag booktag3 = new BookTag();
        booktag3.setTag("历史与叙事");
        BookTag booktag4 = new BookTag();
        booktag4.setTag("诗歌");
        BookTag booktag5 = new BookTag();
        booktag5.setTag("心理与存在主义");
        BookTag booktag6 = new BookTag();
        booktag6.setTag("成长与自我发现");

        booktag2.addBookId(1L);
        booktag6.addBookId(2L);
        booktag1.addBookId(3L);
        booktag2.addBookId(4L);
        booktag1.addBookId(7L);
        booktag2.addBookId(9L);
        booktag3.addBookId(10L);
        booktag4.addBookId(11L);
        booktag2.addBookId(13L);
        booktag5.addBookId(14L);
        booktag6.addBookId(16L);
        booktag3.addBookId(19L);
        booktag4.addBookId(20L);
        booktag5.addBookId(21L);
        booktag6.addBookId(22L);
        booktag1.addBookId(23L);
        booktag2.addBookId(24L);

        booktag1.addRelatedTag(booktag4);
        booktag1.addRelatedTag(booktag5);
        booktag2.addRelatedTag(booktag3);
        booktag2.addRelatedTag(booktag5);
        booktag3.addRelatedTag(booktag1);
        booktag4.addRelatedTag(booktag6);
        booktag5.addRelatedTag(booktag6);

        bookTagRepository.save(booktag1);
        bookTagRepository.save(booktag2);
        bookTagRepository.save(booktag3);
        bookTagRepository.save(booktag4);
        bookTagRepository.save(booktag5);
        bookTagRepository.save(booktag6);

        return null;
    }

    @QueryMapping
    public Book getBookByName(@Argument String name)
    {
        return bookService.getBookByName(name);
    }
}

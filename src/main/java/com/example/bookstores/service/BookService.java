package com.example.bookstores.service;

import com.example.bookstores.entity.Book;
import com.example.bookstores.util.msg.Msg;
import com.example.bookstores.util.request.BookForm.BookAmountPrice;
import com.example.bookstores.util.request.BookForm.GerUserStatisticsForm;
import com.example.bookstores.util.request.BookForm.GetUserBookForm;

import java.util.Date;
import java.util.List;

public interface BookService {
    Book getBookById(Long id);

    Book getBookByAuthor(String author);

    List<Book> getBooks();

    Msg updateBook(Book book);

    void deleteBook(Long id);

    Msg addBook(Book book);

    List<BookAmountPrice> getBookStatistics(Date beginTime, Date endTime);
    List<GerUserStatisticsForm> getUserStatistics(Date beginTime, Date endTime);
    GetUserBookForm getUserBookForms(Date beginTime, Date endTime, Long userId);

}

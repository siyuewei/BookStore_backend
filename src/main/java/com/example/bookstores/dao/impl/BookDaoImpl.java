package com.example.bookstores.dao.impl;

import com.example.bookstores.dao.BookDao;
import com.example.bookstores.entity.Book;
import com.example.bookstores.repository.BookRepository;
import com.example.bookstores.util.msg.Msg;
import com.example.bookstores.util.msg.MsgUtil;
import com.example.bookstores.util.redis.RedisUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Repository
public class BookDaoImpl implements BookDao {
    private final BookRepository bookRepository;
    private final RedisUtil redisUtil;
    private final Logger loggerFactory = LoggerFactory.getLogger(BookDaoImpl.class);

    public BookDaoImpl(BookRepository bookRepository,  RedisUtil redisUtil) {
        this.bookRepository = bookRepository;
        this.redisUtil = redisUtil;
    }

    @Override
    public Optional<Book> findBookById(Long id) {
        Book book;
        Object bookCache = redisUtil.get("book:" + id);
        if (bookCache != null) {
            ObjectMapper objectMapper = new ObjectMapper();
            book = objectMapper.convertValue(bookCache, Book.class);
            loggerFactory.info("从缓存中获取了book:" + id);
        } else {
            book = bookRepository.getBookById(id);
            book.setTags(null);
            redisUtil.set("book:" + id, book);
            loggerFactory.info("从数据库中获取了book:" + id);
        }
        return Optional.ofNullable(book);
    }

    @Override
    public Book findBookByAuthor(String author) {
        Book book;
        Object bookCache = redisUtil.get("book_author:" + author);
        if (bookCache != null) {
            ObjectMapper objectMapper = new ObjectMapper();
            book = objectMapper.convertValue(bookCache, Book.class);
            loggerFactory.info("从缓存中获取了book_author:" + author);
        } else {
            book = bookRepository.findBookByAuthor(author);
            book.setTags(null);
            redisUtil.set("book_author:" + author, book);
            loggerFactory.info("从数据库中获取了book_author:" + author);
        }
        return book;
    }

    @Override
    public void addBook(Book book) {
        //数据库中加一份
        bookRepository.save(book);
        //缓存中加一份
        redisUtil.set("book:" + book.getId(), book);
    }

    @Override
    public Book getBookById(Long id) {
        Book book;
        Object bookCache = redisUtil.get("book:" + id);
        if (bookCache != null) {
            ObjectMapper objectMapper = new ObjectMapper();
            book = objectMapper.convertValue(bookCache, Book.class);
            loggerFactory.info("从缓存中获取了book:" + id);
        } else {
            book = bookRepository.getBookById(id);
            book.setTags(null);
            redisUtil.set("book:" + id, book);
            loggerFactory.info("从数据库中获取了book:" + id);
        }
        return book;
    }

    @Override
    public List<Book> getBooks() {
        return bookRepository.findAll();
    }

    @Override
    public Msg updateBook(Book book) {
        List<Book> bookList = bookRepository.findAll();
        for(Book albook:bookList){
            if(!Objects.equals(albook.getId(), book.getId())){
                if(Objects.equals(albook.getName(), book.getName())){
                    return MsgUtil.makeMsg(MsgUtil.ERROR,"已有同名书籍");
                }
                if(Objects.equals(albook.getIsbn(), book.getIsbn())){
                    return MsgUtil.makeMsg(MsgUtil.ERROR,"已有同样的ISBN");
                }
            }
        }

        //数据库中更新
        bookRepository.save(book);
        //缓存中更新
        Object bookCache = redisUtil.get("book:" + book.getId());
        if (bookCache != null) {
            redisUtil.del("book:" + book.getId());
            loggerFactory.info("从redis中删除");
        }
        redisUtil.set("book:" + book.getId(), book);
        loggerFactory.info("添加修改后的到redis中");

        return MsgUtil.makeMsg(MsgUtil.SUCCESS,"修改成功");
    }

    @Override
    public void deleteBook(Long id) {
        //数据库中删除
        Book book = bookRepository.getBookById(id);
        book.setIsDelete(true);
        updateBook(book);
        //缓存中删除
        Object bookCache = redisUtil.get("book:" + id);
        if (bookCache != null) {
            redisUtil.del("book:" + id);
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, isolation = Isolation.READ_COMMITTED)
    public void save(Book book) {
        //数据库中加一份
        bookRepository.save(book);

        //缓存中加一份
        redisUtil.set("book:" + book.getId(), book);
    }
}

package com.example.bookstores.service.Impl;

import com.example.bookstores.dao.BookDao;
import com.example.bookstores.dao.TagDao;
import com.example.bookstores.entity.Book;
import com.example.bookstores.entity.Tag;
import com.example.bookstores.service.BookService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookServiceImpl implements BookService {
    private final BookDao bookDao;
    private final TagDao tagDao;

    public BookServiceImpl(BookDao bookDao, TagDao tagDao) {
        this.bookDao = bookDao;
        this.tagDao = tagDao;
    }

    @Override
    public Book getBookById(Long id) {
        return bookDao.findBookById(id).orElseThrow();
    }

    @Override
    public Book getBookByAuthor(String author) {
        return bookDao.findBookByAuthor(author);
    }

    @Override
    public void addBook(String title, String image, String desc, String author, String isbn, Double price, Integer inventory, String tagName) {
        Tag tag = tagDao.getTagByContent(tagName);
        List<Tag> tags = new ArrayList<>();
        tags.add(tag);
        bookDao.addBook(new Book(title, image, desc, author, isbn, price, inventory,tags));
    }

    @Override
    public List<Book> getBooks() {
        return bookDao.getBooks();
    }


}

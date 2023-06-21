package com.example.bookstores.util.request.BookForm;

import com.example.bookstores.entity.Book;

public class BookAmountPrice{
    Book book;
    Integer amount;
    Double price;
    public BookAmountPrice(Book book,Integer amount,Double price){
        this.book = book;
        this.amount = amount;
        this.price = price;
    }
    public Book getBook(){
        return book;
    }
    public Integer getAmount(){
        return amount;
    }
    public Double getPrice(){
        return price;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }
    public void setPrice(Double price){
        this.price = price;
    }
    public void setBook(Book book){
        this.book = book;
    }
}
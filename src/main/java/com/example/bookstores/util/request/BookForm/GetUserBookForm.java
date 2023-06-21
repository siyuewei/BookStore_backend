package com.example.bookstores.util.request.BookForm;

import com.example.bookstores.entity.Book;
import com.example.bookstores.entity.User;
import org.springframework.data.util.Pair;

import java.util.List;

public class GetUserBookForm {

    private List<BookAmountPrice> bookAmountPrices;
    private double totalConsumption;

    public List<BookAmountPrice> getBookAmountPrices() {
        return bookAmountPrices;
    }

    public double getTotalConsumption() {
        return totalConsumption;
    }

    public void setBookAmountPrices(List<BookAmountPrice> bookAmountPrices) {
        this.bookAmountPrices = bookAmountPrices;
    }

    public void setTotalConsumption(double totalConsumption) {
        this.totalConsumption = totalConsumption;
    }

}

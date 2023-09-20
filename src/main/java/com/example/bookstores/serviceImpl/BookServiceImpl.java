package com.example.bookstores.serviceImpl;

import com.example.bookstores.dao.BookDao;
import com.example.bookstores.dao.OrderDao;
import com.example.bookstores.entity.Book;
import com.example.bookstores.entity.Order;
import com.example.bookstores.entity.User;
import com.example.bookstores.service.BookService;
import com.example.bookstores.util.msg.Msg;
import com.example.bookstores.util.msg.MsgUtil;
import com.example.bookstores.util.request.BookForm.BookAmountPrice;
import com.example.bookstores.util.request.BookForm.GerUserStatisticsForm;
import com.example.bookstores.util.request.BookForm.GetUserBookForm;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class BookServiceImpl implements BookService {
    private final BookDao bookDao;
    private final OrderDao orderDao;

    public BookServiceImpl(BookDao bookDao, OrderDao orderDao) {
        this.bookDao = bookDao;
        this.orderDao = orderDao;
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
    public List<Book> getBooks() {
//        return bookDao.getBooks();
        List<Book> books = bookDao.getBooks();
        books.removeIf(Book::getIsDelete);
        return books;
    }

    @Override
    public void updateBook(Book book) {
        bookDao.updateBook(book);
    }

    @Override
    public void deleteBook(Long id) {
        Book book = bookDao.getBookById(id);
        book.setIsDelete(true);
        bookDao.updateBook(book);
    }

    @Override
    public Msg addBook(Book book) {
        List<Book> books = bookDao.getBooks();
        for (Book bookIn : books) {
            if (bookIn.getName().equals(book.getName())) {
                return MsgUtil.makeMsg(MsgUtil.ERROR, "已有同名书籍");
            }
            if(Objects.equals(bookIn.getIsbn(), book.getIsbn())){
                return MsgUtil.makeMsg(MsgUtil.ERROR, "已有ISBN相同的书籍");
            }
        }
        bookDao.addBook(book);
        return MsgUtil.makeMsg(MsgUtil.SUCCESS, "添加成功");
    }

    @Override
    public List<BookAmountPrice> getBookStatistics(Date beginTime, Date endTime) {
        List<BookAmountPrice> bookStatistics = new ArrayList<>();
        List<Order> orders = orderDao.getOrdersByTime(beginTime, endTime);
        orders.forEach(order -> {
            order.getOrderItems().forEach(orderItem -> {
                Book book = orderItem.getBook();
                Integer amount = orderItem.getAmount();
                Double price = orderItem.getPrice();

                boolean isExist = false;
                for (BookAmountPrice bookStatistic : bookStatistics) {
                    if (bookStatistic.getBook().equals(book)) {
                        bookStatistic.setAmount(bookStatistic.getAmount() + amount);
                        bookStatistic.setPrice(bookStatistic.getPrice() + price);
                        isExist = true;
                        break;
                    }
                }
                if(!isExist){
                    bookStatistics.add(new BookAmountPrice(book,amount,price));
                }

            });
        });
        return bookStatistics;
    }

    @Override
    public List<GerUserStatisticsForm> getUserStatistics(Date beginTime, Date endTime) {
        List<GerUserStatisticsForm> userStatistics = new ArrayList<>();
        List<Order> orders = orderDao.getOrdersByTime(beginTime, endTime);
        orders.forEach(order -> {
            User user = order.getUser();
            Double totalPrice = order.getTotalPrice();
//            List<BookAmountPrice> bookAmountPrices = new ArrayList<>();
//            order.getOrderItems().forEach(orderItem -> {
//                Book book = orderItem.getBook();
//                Integer amount = orderItem.getAmount();
//                Double price = orderItem.getPrice();
//                boolean isExist = false;
//                for (BookAmountPrice bookAmountPrice : bookAmountPrices) {
//                    if (bookAmountPrice.getBook().equals(book)) {
//                        bookAmountPrice.setAmount(bookAmountPrice.getAmount() + amount);
//                        bookAmountPrice.setPrice(bookAmountPrice.getPrice() + price);
//                        isExist = true;
//                        break;
//                    }
//                }
//                if (!isExist) {
//                    bookAmountPrices.add(new BookAmountPrice(book, amount, price));
//                }
//            });

            boolean isExist = false;
            for (GerUserStatisticsForm userStatistic : userStatistics) {
                if (userStatistic.getUser().equals(user)) {
                    userStatistic.setTotalConsumption(userStatistic.getTotalConsumption() + totalPrice);
//                    List<BookAmountPrice> oldBookAmounts = userStatistic.getBookAmountPrices();
//                    for(BookAmountPrice bookAmountPrice: bookAmountPrices){
//                        boolean isExistBook = false;
//                        for(BookAmountPrice oldBookAmount: oldBookAmounts){
//                            if(bookAmountPrice.getBook().equals(oldBookAmount.getBook())){
//                                oldBookAmount.setAmount(oldBookAmount.getAmount() + bookAmountPrice.getAmount());
//                                oldBookAmount.setPrice(oldBookAmount.getPrice() + bookAmountPrice.getPrice());
//                                isExistBook = true;
//                            }
//                        }
//                        if(!isExistBook){
//                            oldBookAmounts.add(bookAmountPrice);
//                        }
//                    }
//                    userStatistic.setBookAmountPrices(oldBookAmounts);
                    isExist = true;
                    break;
                }
            }
            if(!isExist){
                userStatistics.add(new GerUserStatisticsForm(user,totalPrice
//                        , bookAmountPrices
                ));
            }
        });

        return userStatistics;
    }

    @Override
    public GetUserBookForm getUserBookForms(Date beginTime, Date endTime, Long userId) {
        GetUserBookForm userBookForms = new GetUserBookForm();
        userBookForms.setBookAmountPrices(new ArrayList<>());
        List<Order> orders = orderDao.getOrdersByTimeAndUserId(beginTime, endTime, userId);
        orders.forEach(order -> {
            Double totalPrice = order.getTotalPrice();
            userBookForms.setTotalConsumption(userBookForms.getTotalConsumption() + totalPrice);

            order.getOrderItems().forEach(orderItem -> {
                Book book = orderItem.getBook();
                Integer amount = orderItem.getAmount();
                Double price = orderItem.getPrice();

                boolean isExist = false;
                for(BookAmountPrice bookAmountPrice: userBookForms.getBookAmountPrices()){
                    if(bookAmountPrice.getBook().equals(book)){
                        Integer newAmount = bookAmountPrice.getAmount() + amount;
                        Double newPrice = bookAmountPrice.getPrice() + price;
                        userBookForms.getBookAmountPrices().remove(bookAmountPrice);
                        userBookForms.getBookAmountPrices().add(new BookAmountPrice(book,newAmount,newPrice));
                        isExist = true;
                        break;
                    }
                }
                if(!isExist){
                    userBookForms.getBookAmountPrices().add(new BookAmountPrice(book,amount,price));
                }
            });
        });

        return userBookForms;
    }
}

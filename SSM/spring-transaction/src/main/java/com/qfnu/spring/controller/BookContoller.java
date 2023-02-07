package com.qfnu.spring.controller;

import com.qfnu.spring.service.BookService;
import com.qfnu.spring.service.CheckOutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class BookContoller {

    @Autowired
    private BookService bookService;
    @Autowired
    private CheckOutService checkOutService;

    public void buyBook(Integer userId,Integer bookId){
        bookService.buyBook(userId,bookId);
    }

    public void checkout(Integer userId,Integer[] bookIds){
        checkOutService.checkout(userId,bookIds);
    }
}

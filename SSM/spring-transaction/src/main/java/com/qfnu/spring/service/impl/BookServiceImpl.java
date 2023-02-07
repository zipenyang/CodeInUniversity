package com.qfnu.spring.service.impl;

import com.qfnu.spring.dao.BookDao;
import com.qfnu.spring.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.TimeUnit;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookDao bookDao;

    @Override
    /*@Transactional*/
    public void buyBook(Integer userId, Integer bookId) {
        //查询图书的价格
        Integer price = bookDao.getPriceByBookId(bookId);
        //更新图书的库存
        bookDao.getStockByBookId(bookId);
        //更新用户的余额
        bookDao.updateBalance(userId,price);
    }
}

package com.qfnu.spring.service.impl;

import com.qfnu.spring.dao.CheckOutDao;
import com.qfnu.spring.service.BookService;
import com.qfnu.spring.service.CheckOutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CheckOutServiceImpl implements CheckOutService {

    @Autowired
    private BookService bookService;
    @Override
/*    @Transactional*/
    public void checkout(Integer userId, Integer[] bookIds) {
        for (Integer bookId : bookIds) {
            bookService.buyBook(userId,bookId);
        }
    }
}

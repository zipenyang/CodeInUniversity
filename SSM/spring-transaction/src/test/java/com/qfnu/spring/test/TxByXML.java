package com.qfnu.spring.test;

import com.qfnu.spring.controller.BookContoller;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:tx-xml.xml")
public class TxByXML {

    @Autowired
    private BookContoller bookContoller;

    @Test
    public void testBuyBook(){
        bookContoller.buyBook(1,1);
    }
}

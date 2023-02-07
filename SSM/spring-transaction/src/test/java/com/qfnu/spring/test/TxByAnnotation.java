package com.qfnu.spring.test;

import com.qfnu.spring.controller.BookContoller;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:tx-annotation.xml")
public class TxByAnnotation {

    @Autowired
    private BookContoller bookContoller;

    @Test
    public void testBuyBook(){
        bookContoller.checkout(1,new Integer[]{1,2});
    }

}

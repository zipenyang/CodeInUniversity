package com.qfnu.controller;


import com.qfnu.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class HelloController {

    @Autowired
    private HelloService helloService;
}

package com.qfnu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TestViewController {

    @RequestMapping("/test/view/thymeleaf")
    public String testThymeleaf(){
        return "success";
    }

    @RequestMapping("/test/view/forward")
    public String testInternalResourceView(){
        return "forward:/param/pojo";
    }

    @RequestMapping("/test/view/redirect")
    public String testRedirectView(){
        return "redirect:/param/pojo";
    }



}

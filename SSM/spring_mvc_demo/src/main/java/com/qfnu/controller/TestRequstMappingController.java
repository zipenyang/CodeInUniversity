package com.qfnu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class TestRequstMappingController {

    @RequestMapping(value = {"/hello","/abc"},
                    method = {RequestMethod.GET,RequestMethod.POST})
    public String hello(){
        return "success";
    }

    @RequestMapping("/a?c/test/ant")
    public String testAnt(){
        return "success";
    }

    @RequestMapping("/test/rest/{username}/{id}")
    public String testRest(@PathVariable("id") Integer id,@PathVariable("username" ) String username){
        System.out.println("id:"+id+","+"username:"+username);
        return "success";
    }
}

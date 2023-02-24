package com.qfnu.springboot.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class MyExceptionHandler {

    /*此种写法，浏览器和客户端返回的都是json数据*/
/*    @ResponseBody
    @ExceptionHandler(Exception.class)
    public Map<String, Object> handleException(Exception e){
        Map<String,Object> hashmap = new HashMap<>();
        hashmap.put("code","user.notexist");
        hashmap.put("message",e.getMessage());
        return hashmap;
    }*/

    @ResponseBody
    @ExceptionHandler(Exception.class)
    public String handleException(Exception e, HttpServletRequest request){
        Map<String,Object> hashmap = new HashMap<>();
        /*传入我们自己的错误状态码，否则就不会进入定制错误页面的解析流程*/
        request.setAttribute("javax.servlet.error.status_code",500);
        hashmap.put("code","user.notexist");
        hashmap.put("message",e.getMessage());
        //转发到/error
        return "forward:/error";
    }
}

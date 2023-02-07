package com.qfnu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class TestRestController {

    @RequestMapping(value = "/user",method = RequestMethod.GET)
    public String getAllUser(){
        System.out.println("查询所有用户信息-->/user-->get");
        return "success";
    }

    @RequestMapping(value = "/user/{id}",method = RequestMethod.GET)
    public String getUserById(@PathVariable("id") Integer id){
        System.out.println("根据id查询用户信息-->/user/"+id+"-->get");
        return "success";
    }

    @RequestMapping(value = "/user",method = RequestMethod.POST)
    public String InsertUser(){
        System.out.println("添加用户信息-->/user-->post");
        return "success";
    }

    @RequestMapping(value = "/user",method = RequestMethod.PUT)
    public String UpdateUser(){
        System.out.println("修改用户信息-->/user-->put");
        return "success";
    }

    @RequestMapping(value = "/user/{id}",method = RequestMethod.DELETE)
    public String DeleteUser(@PathVariable("id") Integer id){
        System.out.println("删除用户信息-->/user/"+id+"-->delete");
        return "success";
    }


}

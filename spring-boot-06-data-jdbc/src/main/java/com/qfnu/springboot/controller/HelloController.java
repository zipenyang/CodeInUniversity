package com.qfnu.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Map;
import java.util.function.ObjIntConsumer;

@Controller
public class HelloController {
    @Autowired
    JdbcTemplate jdbcTemplate;

    @GetMapping("/query")
    public Map<String,Object> map(){
        List<Map<String, Object>> maps = jdbcTemplate.queryForList("SELECT * FROM admin");
        return maps.get(0);
    }
}

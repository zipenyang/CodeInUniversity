package com.qfnu.spring.test;

import com.qfnu.spring.pojo.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

//指定当前测试类在spring的环境中执行，此时就可以通过注入的方式直接获取IOC种的Bean
@RunWith(SpringJUnit4ClassRunner.class)
//设置spring测试环境的配置文件
@ContextConfiguration("classpath:spring-jdbc.xml")
public class JdbcTemplateTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    public void testInsert(){
        String sql = "INSERT INTO t_user VALUES(null,?,?,?,?,?)";
        jdbcTemplate.update(sql,"root","123",23,"女","123@qq.com");
    }

    @Test
    public void testGetUserById(){
        String sql = "SELECT * FROM t_user WHERE id = ?";
        User user = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(User.class), 1);
        System.out.println(user);
    }

    @Test
    public void testGetAllUser(){
        String sql = "SELECT * FROM t_user";
        List<User> uesrs = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(User.class));
        uesrs.forEach(System.out::println);
    }

    @Test
    public void testGetCount(){
        String sql = "SELECT COUNT(*) FROM t_user";
        Integer count = jdbcTemplate.queryForObject(sql,Integer.class);
        System.out.println(count);
    }


}

package com.qfnu.test;

import com.qfnu.mapper.SpecialSQLMapper;
import com.qfnu.pojo.User;
import com.qfnu.utils.SqlSessionUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.List;

public class SpecialSQLMapperTest {
    @Test
    public void testGetUserByLike(){
        SqlSession sqlSession = SqlSessionUtil.getSqlSession();
        SpecialSQLMapper sqlMapper = sqlSession.getMapper(SpecialSQLMapper.class);
        List<User> users = sqlMapper.getUserByLike("a");
        users.forEach(System.out::println);
    }

    @Test
    public void testDeleteMoreUser(){
        SqlSession sqlSession = SqlSessionUtil.getSqlSession();
        SpecialSQLMapper sqlMapper = sqlSession.getMapper(SpecialSQLMapper.class);
        sqlMapper.deleteMoreUser("3,4");
    }

    @Test
    public void testGetUserList(){
        SqlSession sqlSession = SqlSessionUtil.getSqlSession();
        SpecialSQLMapper sqlMapper = sqlSession.getMapper(SpecialSQLMapper.class);
        List<User> users = sqlMapper.getUserList("t_user");
        users.forEach(System.out::println);
    }

    @Test
    public void testInsertUser(){
        SqlSession sqlSession = SqlSessionUtil.getSqlSession();
        SpecialSQLMapper sqlMapper = sqlSession.getMapper(SpecialSQLMapper.class);
        User user = new User(null,"xiaoming","123456",22,"女","123@qq.com");
        sqlMapper.insertUser(user);
        System.out.println(user);
    }

}

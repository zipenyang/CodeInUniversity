package com.qfnu.test;

import com.qfnu.mapper.SelectMapper;
import com.qfnu.pojo.User;
import com.qfnu.utils.SqlSessionUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.List;
import java.util.Map;

public class SelectMapperTest {

    @Test
    public void testGetUserById(){
       SqlSession sqlSession = SqlSessionUtil.getSqlSession();
       SelectMapper mapper = sqlSession.getMapper(SelectMapper.class);
       User user = mapper.getUserById(1);
        System.out.println(user);
    }


    @Test
    public void testGetAllUser(){
        SqlSession sqlSession = SqlSessionUtil.getSqlSession();
        SelectMapper mapper = sqlSession.getMapper(SelectMapper.class);
        List<User> list = mapper.getAllUser();
        list.forEach(System.out::println);
    }


    @Test
    public void testGetCount(){
        SqlSession sqlSession = SqlSessionUtil.getSqlSession();
        SelectMapper mapper = sqlSession.getMapper(SelectMapper.class);
        int count = mapper.getCount();
        System.out.println(count);
    }


    @Test
    public void testGetUserByIdToMap(){
        SqlSession sqlSession = SqlSessionUtil.getSqlSession();
        SelectMapper mapper = sqlSession.getMapper(SelectMapper.class);
        Map<String,Object> map = mapper.getUserByIdToMap(1);
        //{password=123456, gender=男, id=1, age=23, email=12345@qq.com, username=admin}
        System.out.println(map);
    }


    //使用list集合存储map
    /*public void testGetAllUserToMap(){
        SqlSession sqlSession = SqlSessionUtil.getSqlSession();
        SelectMapper mapper = sqlSession.getMapper(SelectMapper.class);
        List<Map<String, Object>> list = mapper.getAllUserToMap();
        [{password=123456, gender=男, id=1, age=23, email=12345@qq.com, username=admin},
        {password=1234567, gender=男, id=2, age=33, email=123456@qq.com, username=admin}]
        System.out.println(list);
    }*/


    //直接使用map输出，@MapKey("id")规定使用id作为map的key值，其余属性为value
    @Test
    public void testGetAllUserToMap(){
        SqlSession sqlSession = SqlSessionUtil.getSqlSession();
        SelectMapper mapper = sqlSession.getMapper(SelectMapper.class);
        Map<String, Object> map = mapper.getAllUserToMap();
        System.out.println(map);
    }



}

package com.qfnu.mapper;

import com.qfnu.pojo.User;
import org.apache.ibatis.annotations.Param;

import java.util.Map;
import java.util.Objects;

public interface UserMapper {

    User getUserByUsername(String username);

    User chechLogin(String username,String password);

    User checkLoginByMap(Map<String, Object> map);

    void insertUser(User user);

    //User checkLoginByParam(String username,String password);
    User checkLoginByParam(@Param("username") String username,@Param("password") String password);
}

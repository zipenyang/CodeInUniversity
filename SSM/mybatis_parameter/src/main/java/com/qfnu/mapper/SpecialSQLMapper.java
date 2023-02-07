package com.qfnu.mapper;

import com.qfnu.pojo.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface SpecialSQLMapper {

    List<User> getUserByLike(@Param("mohu") String mohu);

    void deleteMoreUser(@Param("ids") String ids);


    List<User> getUserList(@Param("tableName") String tableName);


    void insertUser(User user);
}

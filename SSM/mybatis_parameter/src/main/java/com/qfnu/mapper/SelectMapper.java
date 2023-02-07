package com.qfnu.mapper;

import com.qfnu.pojo.User;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface SelectMapper {
    //根据id查询用户信息
    User getUserById(@Param("id") Integer id);

    //查询所有用户信息
    List<User> getAllUser();

    Integer getCount();


    Map<String,Object> getUserByIdToMap(@Param("id") Integer id);

    //使用list集合存储map
    //List<Map<String,Object>> getAllUserToMap();

    @MapKey("id")
    Map<String,Object> getAllUserToMap();
}

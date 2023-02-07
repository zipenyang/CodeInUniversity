package com.qfnu.spring.dao.impl;

import com.qfnu.spring.dao.UserDao;

public class UserDaoImpl implements UserDao {

    @Override
    public void saveUser(){
        System.out.println("保存成功");
    }
}

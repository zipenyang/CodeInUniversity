package com.qfnu.spring.dao.impl;

import com.qfnu.spring.dao.UserDao;
import org.springframework.stereotype.Repository;


@Repository
public class UserDaoImpl implements UserDao {

    @Override
    public void saveUser() {
        System.out.println("保存成功！！！");
    }
}

package com.qfnu.spring.service.impl;

import com.qfnu.spring.dao.UserDao;
import com.qfnu.spring.service.UserService;

public class UserServiceImpl implements UserService {


    private UserDao userDao;

    public UserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public void saveUser() {
        userDao.saveUser();
    }
}

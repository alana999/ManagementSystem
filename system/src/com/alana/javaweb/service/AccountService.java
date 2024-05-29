package com.alana.javaweb.service;

import com.alana.javaweb.dao.UserDao;
import com.alana.javaweb.model.User;
import com.alana.javaweb.utils.HashUtil;

public class AccountService {
    private UserDao userDao = new UserDao();

    public User login(String username, String password) {
        User user = userDao.selectByUsername(username);
        if (user != null && HashUtil.verifyPassword(password, user.getPassword())) {
            return user;
        }
        return user;
    }

    public void register(String username, String password) {
        String hashedPassword = HashUtil.hashPassword(password);
        User user = new User(username, hashedPassword);
        userDao.insert(user);
    }
}


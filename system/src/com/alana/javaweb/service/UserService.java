package com.alana.javaweb.service;

import com.alana.javaweb.dao.UserDao;
import com.alana.javaweb.model.User;

public class UserService {

    private UserDao userDao;

    public UserService() {
        this.userDao = new UserDao();
    }

    public User authenticateUser(String username, String password) {
        User user = userDao.selectByUsername(username);
        if (user != null && user.getPassword().equals(password)) {
            return user;
        }
        return null;
    }
}


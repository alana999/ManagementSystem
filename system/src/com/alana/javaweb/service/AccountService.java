package com.alana.javaweb.service;

import com.alana.javaweb.dao.UserDao;
import com.alana.javaweb.exception.BusinessException;
import com.alana.javaweb.model.Group;
import com.alana.javaweb.model.User;
import com.alana.javaweb.utils.HashUtil;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.sql.SQLException;

public class AccountService {
    private UserDao userDao = new UserDao();

    public User login(String username, String password) {
        User user = userDao.selectByUsername(username);
        if (user != null && HashUtil.verifyPassword(password, user.getPassword())) {
            return user;
        }
        return user;
    }

    public boolean register(String username, String password) {
        String hashedPassword = HashUtil.hashPassword(password);
        User user = new User(username, hashedPassword);
        try {
            userDao.insert(user);
            return true;
        } catch (SQLException e) {
            // 转换 SQLException 为 BusinessException
            throw new BusinessException("Registration failed: " + e.getMessage());
        }

    }

    public void exit(HttpServletRequest request, HttpServletResponse response) {

        // 获取session对象，销毁session
        HttpSession session = request.getSession(false);
        if (session != null) {

            // 从session域中删除user对象
            session.removeAttribute("user");

            // 手动销毁session对象。
            session.invalidate();

            // 销毁cookie（退出系统将所有的cookie全部销毁）
            Cookie[] cookies = request.getCookies();
            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    // 设置cookie的有效期为0，表示删除该cookie
                    cookie.setMaxAge(0);
                    // 设置一个下cookie的路径
                    cookie.setPath(request.getContextPath()); // 删除cookie的时候注意路径问题。
                    // 响应cookie给浏览器，浏览器端会将之前的cookie覆盖。
                    response.addCookie(cookie);
                }
            }

        }
    }

    public User getUserByUsername(String username) {
        return userDao.selectByUsername(username);
    }

    public boolean updateAccount(User user) {
        String hashedPassword = HashUtil.hashPassword(user.getPassword());
        user.setPassword(hashedPassword);
        return userDao.update(user) > 0;
    }

    public void LoginFree(HttpServletRequest request,HttpServletResponse response,User user){
        // 创建Cookie对象存储登录名
        Cookie cookie1 = new Cookie("username", user.getUsername());
        // 创建Cookie对象存储密码
        Cookie cookie2 = new Cookie("password", user.getPassword()); // 真实情况下是加密的。
        // 设置cookie的有效期为十天
        cookie1.setMaxAge(60 * 60 * 24 * 10);
        cookie2.setMaxAge(60 * 60 * 24 * 10);
        // 设置cookie的path（只要访问这个应用，浏览器就一定要携带这两个cookie）
        cookie1.setPath(request.getContextPath());
        cookie2.setPath(request.getContextPath());
        // 响应cookie给浏览器
        response.addCookie(cookie1);
        response.addCookie(cookie2);
    }

}


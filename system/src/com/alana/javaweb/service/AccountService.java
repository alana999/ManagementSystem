package com.alana.javaweb.service;

import com.alana.javaweb.dao.UserDao;
import com.alana.javaweb.model.Group;
import com.alana.javaweb.model.User;
import com.alana.javaweb.utils.HashUtil;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

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

}


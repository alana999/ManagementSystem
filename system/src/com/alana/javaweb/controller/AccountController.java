package com.alana.javaweb.controller;

import com.alana.javaweb.model.User;
import com.alana.javaweb.service.AccountService;
import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

//处理用户登录、注册和退出。
@WebServlet("/account/*")
public class AccountController extends HttpServlet {
    private AccountService accountService = new AccountService();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getPathInfo();

        if ("/login".equals(action)) {
            handleLogin(req, resp);
        } else if ("/register".equals(action)) {
            handleRegister(req, resp);
        } else if ("/logout".equals(action)) {
            handleExit(req, resp);
        }
    }


    private void handleLogin(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        User user = accountService.login(username, password);
        if (user != null) {
            req.getSession().setAttribute("user", user);
            resp.sendRedirect("/sys/index.jsp");
        } else {
            req.setAttribute("errorMessage", "Invalid login");
            req.getRequestDispatcher("login.jsp").forward(req, resp);
        }
    }

    private void handleRegister(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String username = req.getParameter("username");
        String password = req.getParameter("password");
        accountService.register(username, password);
        resp.sendRedirect(req.getContextPath() + "/login.jsp");

    }

    /**
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void handleExit(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        accountService.exit(request, response);
        // 跳转到登录页面
        response.sendRedirect(request.getContextPath() + "/login.jsp");
    }
}

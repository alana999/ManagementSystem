<%--
  Created by IntelliJ IDEA.
  User: Alana
  Date: 2024/5/25
  Time: 16:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <title>注册</title>
    <link rel="stylesheet" href="static/css/bootstrap.min.css">
</head>
<body>
<div class="container">
    <h2>注册</h2>
    <form action="account/register" method="post">
        <div class="form-group">
            <label for="username">用户名:</label>
            <input type="text" class="form-control" id="username" name="username" required>
        </div>
        <div class="form-group">
            <label for="password">密码:</label>
            <input type="password" class="form-control" id="password" name="password" required>
        </div>
        <div class="form-group">
            <button type="submit" class="btn btn-primary">注册</button>
        </div>
    </form>
    <div class="text-center">
        已有账号? <a href="login.jsp">登录</a>
    </div>
</div>
</body>
</html>

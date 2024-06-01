<%--
  Created by IntelliJ IDEA.
  User: Alana
  Date: 2024/5/25
  Time: 16:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="zh-CN">
<head>
  <meta charset="UTF-8">
  <title>科协管理系统</title>
  <link rel="stylesheet" href="static/css/bootstrap.min.css">
</head>
<body>
<div class="container">
  <h1>欢迎来到科协管理系统</h1>
  <div class="mb-3">
    <span>欢迎, ${sessionScope.user.username}!</span>
    <a href="account/logout" class="btn btn-danger">登出</a>
  </div>
  <div class="list-group">
    <a href="account/edit" class="list-group-item list-group-item-action">账户信息修改</a>
    <a href="group/list" class="list-group-item list-group-item-action">团队管理</a>
    <a href="member/list" class="list-group-item list-group-item-action">成员管理</a>
  </div>
</div>
</body>
</html>

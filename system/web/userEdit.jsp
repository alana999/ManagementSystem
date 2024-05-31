<%--
  Created by IntelliJ IDEA.
  User: Alana
  Date: 2024/5/31
  Time: 17:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>编辑账户</title>
</head>
<body>
<form action="update" method="post">
    <input type="hidden" name="action" value="update">
    账户ID：<input type="text" name="userId" value="${user.userId}" readonly><br>
    用户名：<input type="text" name="username" value="${user.username}"><br>
    密码：<input type="password" name="password"><br>
<%--    邮箱：<input type="email" name="email" value="${user.email}"><br>--%>
    <input type="submit" value="更新">
</form>
</body>
</html>


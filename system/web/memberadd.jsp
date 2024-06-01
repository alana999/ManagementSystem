<%--
  Created by IntelliJ IDEA.
  User: Alana
  Date: 2024/5/31
  Time: 21:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>添加成员</title>
</head>
<body>
<h1>添加新成员</h1>
<% if (request.getAttribute("error") != null) { %>
<p style="color: red;"><%= request.getAttribute("error") %></p>
<% } %>

<form action="${pageContext.request.contextPath}/member/add" method="post">
  <input type="hidden" name="action" value="add">
  成员名字：<input type="text" name="name" required><br>
  小组ID：<input type="number" name="group_id" required><br>
  <input type="submit" value="添加成员">
</form>
</body>
</html>

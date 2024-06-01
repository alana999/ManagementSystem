<%--
  Created by IntelliJ IDEA.
  User: Alana
  Date: 2024/6/1
  Time: 10:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>编辑成员</title>
</head>
<body>
<form action="update" method="post">
    成员编号<input type="text" name="memberId" value="${member.memberId}" readonly /><br>
    成员名字<input type="text" name="name" value="${member.name}" /><br>
    小组ID：<input type="text" name="groupId" value="${member.groupId}" /><br>
    <input type="submit" value="更新">
</form>
</body>
</html>

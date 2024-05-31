<%--
  Created by IntelliJ IDEA.
  User: Alana
  Date: 2024/5/29
  Time: 20:21
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>编辑团队</title>
</head>
<body>
<form action="update" method="post">
    团队编号<input type="text" name=groupId value="${group.groupId}" readonly /><br>
    团队名：<input type="text" name="groupName" value="${group.groupName}" /><br>
    描述：<textarea name="description" value="${group.description}"></textarea><br>
    <input type="submit" value="更新">
</form>
</body>
</html>


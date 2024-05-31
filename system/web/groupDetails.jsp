<%--
  Created by IntelliJ IDEA.
  User: Alana
  Date: 2024/5/31
  Time: 19:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>团队详情</title>
</head>
<body>
<h1>团队详情</h1>
<% if (request.getAttribute("group") != null) { %>
<p>团队名: ${group.groupName}</p>
<p>描述: ${group.description}</p>
<% } else { %>
<p>未找到指定的团队。</p>
<% } %>
<a href="${pageContext.request.contextPath}/group/list">返回团队列表页面</a>
</body>
</html>


<%--
  Created by IntelliJ IDEA.
  User: Alana
  Date: 2024/5/25
  Time: 16:29
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>团队管理</title>
</head>
<body>
<div>

    <h5>查询团队</h5>
    <form action="search" method="get">
        <input type="hidden" name="action" value="search">
        团队名：<input type="text" name="groupName" required>
        <input type="submit" value="查询">
    </form>

    <a href="${pageContext.request.contextPath}/add.jsp">添加新团队</a>
    <h1>团队列表</h1>
    <% if (request.getAttribute("error") != null) { %>
    <p style="color: red;"><%= request.getAttribute("error") %></p>
    <% } %>
    <table border="1" width="80%" align="center">
        <tr>
            <th>团队ID</th>
            <th>团队名</th>
            <th>描述</th>
            <th>创建时间</th>
            <th>更新时间</th>
            <th>操作</th>
        </tr>
        <c:forEach items="${groupList}" var="group">
            <tr>
                <td>${group.groupId}</td>
                <td>${group.groupName}</td>
                <td>${group.description}</td>
                <td>${group.createdAt}</td>
                <td>${group.updatedAt}</td>
                <td>
                    <a href="${pageContext.request.contextPath}/group/edit?groupId=${group.groupId}">编辑</a>

                    <a href="javascript:void(0);" onclick="confirmDelete(${group.groupId})">删除</a>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>
<script type="text/javascript">
    function confirmDelete(groupId) {
        if (confirm('确定要删除这个团队吗？')) {
            document.location.href = '${pageContext.request.contextPath}/group/delete?groupId=' + groupId;
        }
    }
</script>
</body>
</html>

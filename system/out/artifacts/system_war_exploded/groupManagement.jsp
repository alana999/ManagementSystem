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
    <h1>团队列表</h1>
    <a href="add.jsp">添加新团队</a>
    <table border="1" width="80%" align="center">
        <tr>
            <th>团队名</th>
            <th>描述</th>
            <th>操作</th>
        </tr>
        <c:forEach items="${groupList}" var="group">
            <tr>
                <td>${group.groupName}</td>
                <td>${group.description}</td>
                <td>
                    <a href="edit.jsp?groupId=${group.groupId}">编辑</a>
                    <a href="javascript:void(0);" onclick="confirmDelete(${group.groupId})">删除</a>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>
<script type="text/javascript">
    function confirmDelete(groupId) {
        if (confirm('确定要删除这个团队吗？')) {
            document.location.href = 'GroupController?action=delete&groupId=' + groupId;
        }
    }
</script>
</body>
</html>

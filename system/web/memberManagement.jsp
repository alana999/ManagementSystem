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
    <title>成员管理</title>
</head>
<body>

<a href="${pageContext.request.contextPath}/memberadd.jsp">添加新成员</a>
<h5>查询成员</h5>
<form action="${pageContext.request.contextPath}/member/search" method="get">
    <input type="hidden" name="action" value="search">
    成员名：<input type="text" name="name" required>
    <input type="submit" value="查询">
</form>
<h5>成员列表</h5>
<table border="1" width="80%" align="center">
    <tr>
        <th>成员ID</th>
        <th>成员名</th>
        <th>团队ID</th>
        <th>加入时间</th>
        <th>更新时间</th>
        <th>操作</th>
    </tr>
    <c:forEach items="${memberList}" var="member">
        <tr>
            <td>${member.memberId}</td>
            <td>${member.name}</td>
            <td>${member.groupId}</td>
            <td>${member.joinTime}</td>
            <td>${member.updatedAt}</td>
            <td>
                <a href="${pageContext.request.contextPath}/member/edit?memberId=${member.memberId}">编辑</a>
                <a href="javascript:void(0);" onclick="confirmDelete(${member.memberId})">删除</a>
            </td>
        </tr>
    </c:forEach>
</table>

<script type="text/javascript">
    function confirmDelete(memberId) {
        if (confirm("确定要删除该成员吗？")) {
            window.location.href = "${pageContext.request.contextPath}/member/delete?memberId=" + memberId;
        }
    }
</script>


</body>
</html>


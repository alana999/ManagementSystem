<%--
  Created by IntelliJ IDEA.
  User: Alana
  Date: 2024/6/1
  Time: 11:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>成员详情</title>
</head>
<body>
<h1>成员详情</h1>
<% if (request.getAttribute("member") != null) { %>
<p>成员ID: ${member.memberId}</p>
<p>名称: ${member.name}</p>
<p>组ID: ${member.groupId}</p>
<p>组名称: ${member.groupName}</p>
<%--<p>加入时间: ${member.joinTime}</p>--%>
<%--<p>更新时间: ${member.updatedAt}</p>--%>
<% } else { %>
<p>未找到指定的成员。</p>
<% } %>
<a href="${pageContext.request.contextPath}/member/list">返回成员列表页面</a>
</body>
</html>


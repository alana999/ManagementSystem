<%--
  Created by IntelliJ IDEA.
  User: Alana
  Date: 2024/5/29
  Time: 19:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>添加团队</title>
</head>
<body>
<form action="group/add" method="post">
    团队名：<input type="text" name="groupName"><br>
    描述：<textarea name="description"></textarea><br>
<%--    一个文本区域，供用户输入描述。name="description" 指定了该文本区域的名字，提交时会作为键值对的键。--%>
    <input type="submit" value="提交">
<%--    一个提交按钮，点击该按钮后表单数据会被发送到服务器。--%>


<%--    表单提交后的处理流程--%>
<%--    表单提交：--%>

<%--    用户在输入框和文本区域填写信息，然后点击 提交 按钮。--%>
<%--    浏览器将收集表单中的数据，构建一个 HTTP POST 请求。--%>
<%--    HTTP 请求：--%>

<%--    目标URL为 GroupController?action=add。--%>
<%--    请求体包含表单数据：groupName 和 description。--%>
<%--    服务器接收请求：--%>

<%--    服务器上一个名为 GroupController 的控制器会处理这个请求。--%>
<%--    该控制器解析请求的查询参数和请求体。--%>
<%--    处理请求：--%>

<%--    GroupController 解析 action=add 查询参数，确定要执行的操作是“添加”。--%>
<%--    从请求体中提取表单数据：groupName 和 description。--%>
</form>
</body>
</html>


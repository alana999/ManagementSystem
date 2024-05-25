<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Home</title>
</head>
<body>
<h1>Welcome, <%= request.getSession().getAttribute("user") %></h1>
<p>You are now logged in.</p>
</body>
</html>

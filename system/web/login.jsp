<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Login</title>
    <link rel="stylesheet" href="static/css/bootstrap.min.css">
</head>
<body>
<div class="container">
    <h2>Login</h2>
    <% if (request.getAttribute("error") != null) { %>
    <p style="color: red;"><%= request.getAttribute("error") %></p>
    <% } %>
    <form action="${pageContext.request.contextPath}/account/login" method="post">
        <div class="form-group">
            <label for="username">Username:</label>
            <input type="text" class="form-control" id="username" name="username">
        </div>
        <div class="form-group">
            <label for="password">Password:</label>
            <input type="password" class="form-control" id="password" name="password">
        </div>
        <input type="checkbox" name="f" value="1">十天内免登录<br>
        <button type="submit" class="btn btn-primary">Login</button>
    </form>
    <a href="register.jsp">Register</a>
</div>
</body>
</html>

<%--
  Created by IntelliJ IDEA.
  User: 凌
  Date: 2020/10/10
  Time: 21:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>注册</title>
</head>
<body>

<form action="/user/register" method="post">
    用户名<br>
    <input type="text" name="username" id="username" placeholder="用户名"><br>
    密码<br>
    <input type="password" name="password" id="password" placeholder="密码"><br>
    <input type="submit" value="注册">
</form>
</body>
</html>

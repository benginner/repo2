<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false"%>
<html>
<head>
    <title>登陆</title>
</head>
<body>
<c:if test="${not empty param.msg}">
    <div style="font-size: 12px;color: red">${param.msg}</div>
</c:if>
<form action="/user/login" method="post">
    用户名<br>
    <input type="text" name="username" id="username" placeholder="用户名"><br>
    密码<br>
    <input type="password" name="password" id="password" placeholder="密码"><br>
    <input type="submit" value="登陆"><a href="/view/user/changePassword.jsp" style="font-size: 12px">忘记密码?</a>
</form>
<a href="/view/user/register.jsp">注册</a>

</body>
</html>

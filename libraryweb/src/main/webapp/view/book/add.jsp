<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page isELIgnored="false" %>
<html>
<head>
    <title>添加图书</title>
</head>
<body>
<form action="/book/add" method="post">
    <select name="bookType">
        <option value="">---请选择---</option>
        <c:forEach items="${dictionaryList}" var="dictionary" varStatus="i">
            <option value="${dictionary.id}">${dictionary.name}</option>
        </c:forEach>
    </select>
    书名<input type="text" name="bookname" id="bookname"><br>
    添加数量<input type="text" name="booknumber" id="booknumber"><br>
    <input type="submit" value="提交">


</form>

</body>
</html>

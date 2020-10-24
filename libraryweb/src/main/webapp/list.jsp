<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<!-- 网页使用的语言 -->
<html lang="zh-CN">
<head>
    <!-- 指定字符集 -->
    <meta charset="utf-8">
    <!-- 使用Edge最新的浏览器的渲染方式 -->
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <!-- viewport视口：网页可以根据设置的宽度自动进行适配，在浏览器的内部虚拟一个容器，容器的宽度与设备的宽度相同。
    width: 默认宽度与设备的宽度相同
    initial-scale: 初始的缩放比，为1:1 -->
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>用户信息管理系统</title>


    <!-- 1. 导入CSS的全局样式 -->
    <link href="css/bootstrap.css" rel="stylesheet">
    <!-- 2. jQuery导入，建议使用1.9以上的版本 -->
    <script src="js/jquery-2.1.0.min.js"></script>
    <!-- 3. 导入bootstrap的js文件 -->
    <script src="js/bootstrap.js"></script>

    <style type="text/css">
        td, th {
            text-align: center;
        }
    </style>


</head>
<body>
<div class="container">
    <h3 style="text-align: center">图书展示</h3>
<form id="queryform" action="/showbook">
    书名<input type="text" name="bookName" value="${page.entity.bookType.name}">
    <select name="bookType" >
        <option value="">---请选择---</option>
        <c:forEach items="${dictionaryList}" var="dictionary">
            <c:if test="${dictionary.id==page.entity.bookType.id}">
                <option selected value="${dictionary.id}">${dictionary.name}</option>
            </c:if>
            <c:if test="${dictionary.id!=page.entity.bookType.id}">
                <option value="${dictionary.id}">${dictionary.name}</option>
            </c:if>
        </c:forEach>
    </select>
    <input type="submit" value="查询">
</form>
    <form id="form" action="/user/addbook" method="post">
        <table border="1" class="table table-bordered table-hover">
            <tr class="success">
                <th></th>
                <th>编号</th>
                <th>书名</th>
                <th>类型</th>
                <th>数量</th>
            </tr>

            <c:forEach items="${requestScope.page.list}" var="book" varStatus="s">
                <tr>
                    <td><input type="checkbox" name="uid" value="${book.id}"></td>
                    <td>${s.count}</td>
                    <td>${book.bookname}</td>
                    <td>${book.bookType.name}</td>
                    <td>${book.bookamount}</td>

                </tr>
            </c:forEach>
        </table>
        <input type="submit" value="借阅图书">
    </form>
    <div>
        <nav aria-label="Page navigation">
            <ul class="pagination">
                <li>
                    <c:if test="${requestScope.page.currentPage==1}">
                        <a href="#" aria-label="Previous">
                            <span aria-hidden="true">&laquo;</span>
                        </a>
                    </c:if>
                    <c:if test="${requestScope.page.currentPage!=1}">
                        <a href="${pageContext.request.contextPath}/showbook?currentpage=${requestScope.page.currentPage-1}&row=5"
                           aria-label="Previous">
                            <span aria-hidden="true">&laquo;</span>
                        </a>
                    </c:if>

                </li>
                <c:forEach begin="1" end="${requestScope.page.totalPage}" var="i">
                    <li><a href="${pageContext.request.contextPath}/showbook?currentpage=${i}&rows=5">${i}</a></li>
                </c:forEach>
                <li>
                    <c:if test="${page.currentPage==page.totalPage}">

                        <a href="#"
                           aria-label="Next">
                            <span aria-hidden="true">&raquo;</span>
                        </a>
                    </c:if>
                    <c:if test="${page.currentPage!=page.totalPage}">
                        <a href="${pageContext.request.contextPath}/showbook?currentpage=${requestScope.page.currentPage+1}&row=5"
                           aria-label="Next">
                            <span aria-hidden="true">&raquo;</span>
                        </a>
                    </c:if>

                </li>
                <span style="font-size: 25px;margin-left: 5px ">共${requestScope.page.totalCount}条记录，共${requestScope.totalPage}页</span>
            </ul>

        </nav>

    </div>
</div>
</body>
</html>

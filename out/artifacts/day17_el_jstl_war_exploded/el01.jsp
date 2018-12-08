<%--
  Created by IntelliJ IDEA.
  User: YaYa
  Date: 2018/10/14
  Time: 15:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<%--${sessionScope.user.bir}--%>
<table style="border: 1px solid red">
    <tr>
        <th>姓名</th>
        <th>密码</th>
        <th>生日</th>
    </tr>
    <c:forEach items="${sessionScope.list}" var="user" varStatus="s">

        <c:if test="${s.count%2==0}">
            <tr bgcolor="red">
                <td>${user.username}</td>
                <td>${user.password}</td>
                <td>${user.bir}</td>
            </tr>
        </c:if>
        <c:if test="${s.count%2!=0}">
            <tr bgcolor="green">
                <td>${user.username}</td>
                <td>${user.password}</td>
                <td>${user.bir}</td>
            </tr>
        </c:if>
    </c:forEach>

</table>


</body>
</html>

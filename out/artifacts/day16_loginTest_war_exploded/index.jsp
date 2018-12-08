<%--
  Created by IntelliJ IDEA.
  User: YaYa
  Date: 2018/10/12
  Time: 18:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>$Title$</title>
  </head>
  <body>
  <form action="/day16/checkServlet" method="post">
    <input type="text" name="username" placeholder="用户名"><br>
    <input type="text" name="password" placeholder="密码"><br>
    <img src="/day16/checkCodeServlet" alt=""><br>
    <input type="text" name="cc" placeholder="验证码"><br>
    <input type="submit" value="登录">

  </form>

  </body>
</html>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<a href="account/findAll">findAll</a>

<form action="/account/insert" method="post">
    用户名:<input type="text" name="name">
    金钱:<input type="text" name="money">
    <input type="submit" value="提交">
</form>
</body>
</html>

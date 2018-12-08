<%--
  Created by IntelliJ IDEA.
  User: YaYa
  Date: 2018/10/23
  Time: 9:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>$Title$</title>
    <script src="js/jquery-3.3.1.js"></script>
    <script !src="">
$(function () {
    $("#username").blur(function () {
        var username = $("#username").val();
        var span = $("#s_username");
        $.get("findUsernameServlet",{username:username},function (data) {
            alert(data);
            for (key in data){
                alert(key+":" +data[key])
            }
            if (data.userExist){
                span.css("color","red");
                span.html(data.msg);
            }else {
               span.css("color","green");
               span.html("用户名可用");
            }

        },"json");

    });

})
    </script>
</head>
<body>
<input type="text" id="username"><span id="s_username"></span>
</body>
</html>

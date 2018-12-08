<%--
  Created by IntelliJ IDEA.
  User: YaYa
  Date: 2018/11/17
  Time: 10:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <script src="js/jquery.min.js"></script>
    <script>
        $(function () {
            $("#btn").click(function () {
                $.ajax({
                    url: "user/testAjax",
                    contentType: "application/json;charset=UTF-8",
                    data: '{"uname":"zhangsan","uage":22}',
                    dataType: "json",
                    type: "post",
                    success: function (data) {
                        alert(data);
                        alert(data.uname);
                        alert(data.uage);


                    }

                })

            })

        })
    </script>
</head>
<body>
<h2>Hello World!</h2>
<%--<a href="success">点击访问</a>--%>
<a href="/user/testString">testString</a>
<br>
<a href="/user/testModelAndView">testModelAndView</a>


<br>

<button id="btn">发送ajax请求</button>
<br>
<a href="/user/testException">testException</a>
<a href="/user/testIntercepter">testIntercepter</a>
<a href="/user/delete">delete</a>
<a href="/user/redirect">redirect</a>
<%--
<form action="user/save" method="post">
    username <input type="text" name="username"/><br/>
    password <input type="text" name="password"/><br/>
    money <input type="text" name="money"/><br/>


    uname <input type="text" name="users[0].uname"/><br/>
    uage <input type="text" name="users[0].uage"/><br/>

    uname <input type="text" name="maps['one'].uname"/><br/>
    uage <input type="text" name="maps['one'].uage"/><br/>

    uname <input type="text" name="maps['two'].uname"/><br/>
    uage <input type="text" name="maps['two'].uage"/><br/>
    <input type="submit" value="提交"/>
</form>--%>
</body>
</html>

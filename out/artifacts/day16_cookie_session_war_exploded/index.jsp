<%--
  Created by IntelliJ IDEA.
  User: YaYa
  Date: 2018/10/12
  Time: 9:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>$Title$</title>
  </head>
  <body>
  hello_JSP!

  <hr>
 <%!
  private int c = 10+15;
  %>
  输出25
  <%=
   c
  %>
  <%!
  private int add(int a,int b){
      return a+b;
  }
  %>
  <
  <hr>
  输出add
  <%=
  add(13,16)
  %>

  <hr>
  <%!
  public int jianfa(int a ,int b){
      return a-b;
  }
  %>

  输入减法45-40
  <%=
  jianfa(45,40)
  %>
  </body>
</html>

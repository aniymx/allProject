<%@ page import="java.net.URLDecoder" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.net.URLEncoder" %><%--
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
<% Cookie[] cookies = request.getCookies();
    boolean flag = false;
    if (cookies != null && cookies.length > 0) {
        for (Cookie cookie : cookies) {
            String name = cookie.getName();
            if ("lastTime".equals(name)) {//有
                flag = true;
                String value = cookie.getValue();
                value = URLDecoder.decode(value, "utf-8");
%>
<div style="color: pink;width:100%; text-align: center;">
<h1 >欢迎回来!您上次访问时间为<%=value%></h1>
</div>
<%

                Date date = new Date();
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日 hh:mm:ss");
                String format = simpleDateFormat.format(date);

                format = URLEncoder.encode(format, "utf-8");
                Cookie cookieTime = new Cookie("lastTime", format);
                cookieTime.setMaxAge(-1);
                response.addCookie(cookieTime);
                //获取后解码
                break;

            }

        }
    }
%>
<%
    if (cookies == null || cookies.length == 0 || flag == false) {
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日 hh:mm:ss");
        String format = simpleDateFormat.format(date);

        format = URLEncoder.encode(format, "utf-8");
        Cookie cookieTime = new Cookie("lastTime", format);
        cookieTime.setMaxAge(-1);
        response.addCookie(cookieTime);
%>
<h1>新人欢迎你</h1>
<% }%>
</body>
</html>

<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP '403.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">

  </head>
  
  <body>
    <h1>${session_user_username}你没有权限、请联系管理员添加权限</h1>
    <h2><span></span></h2>
    <script type="text/javascript">
	    if(self!=top){
	    	parent.window.location.replace(window.location.href);
	    }else{
	    	var i = 5;
	    	var timer = setInterval(function(){
	    		var spanDom = document.querySelector("h2 span");
	    		spanDom.innerHTML = i+"秒后自动跳转到首页";
	    		i--;
	    		if(i <= 0){
					clearInterval(timer);
	    			window.location.href = "sysmgr/admin/index";
	    		}
	    	},1000);
	    }
    </script>
  </body>
</html>

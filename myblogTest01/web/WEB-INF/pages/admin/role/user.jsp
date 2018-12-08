<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"
	trimDirectiveWhitespaces="true"%>
<%@include file="/WEB-INF/pages/common/taglib.jsp"%>
<!DOCTYPE HTML>
<html>
<head>
<title></title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<%@include file="/WEB-INF/pages/admin/common/common.jsp"%>
<script type="text/javascript" src="${basePath}/resources/js/tz_page.js"></script>
<style>
#userbox li {
	padding: 10px;
	border-bottom: 1px solid #e5e5e5;
	font-size: 16px;
}

#userbox li span {
	dispaly: inline-block;
	padding: 0 10px
}
#userbox li:hover{background:#333;color:#fff;cursor:pointer;}
#userbox li.on{background:#333;color:#fff;}
</style>
</head>
<body>
	<div id="container">
		<ul id="userbox">
			<c:forEach items="${role_users}" var="user" varStatus="index">
				<label>
					<li><input class="check" type="checkbox" value="${user.id}" /><span>${index.count}</span>${user.username}&nbsp;&nbsp;&nbsp;${user.email}</li>
				</label>
			</c:forEach>
		</ul>
	</div>
	<script type="text/javascript">
		var wpUserRole = {
			save:function(roleId){
				var arr = [];
				var $chcked = $("#userbox").find(".check:checked");
				if($chcked.length==0){
					layer.msg("请选择一个用户进行操作...",4);
					return;
				}
				 $chcked.each(function(){
					arr.push($(this).val()); 
				 });
				wpAjax.request({
					path:adminPath,
					model:"role",
					method:"saveRoleUsers",
					success:function(data){
						if(data == "success"){
							layer.msg("角色分配成功");
							$chcked.parents("li").fadeOut("slow",function(){
								$(this).remove();
							});
						}
						if(data == "fail"){
							layer.msg("角色分配失败");
						}
					}
				}, {users:arr.toString(),rid:roleId})
			}
		}
	</script>
</body>
</html>
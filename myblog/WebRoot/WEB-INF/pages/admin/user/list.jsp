<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
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
	<%@include file="/WEB-INF/pages/admin/common/common.jsp" %>
	<script type="text/javascript" src="${basePath}/resources/js/tz_page.js"></script>
	<style>
		.layui-layer-content p {
			overflow-x:hidden;
		}
		.layui-layer-content p input{
			    width: 100%;
    			margin: 13px 0 0 0px;
		}
	</style>
 </head>
 <body>
	<div class="wrap">
		<!-- left页面 -->
		<%@include file="/WEB-INF/pages/admin/common/left.jsp" %>
		<div class="content">
			<!-- 头部页面 -->
			<%@include file="/WEB-INF/pages/admin/common/header.jsp" %>
			<div class="channel"> 位置 > 内容管理 <a href="javascript:void(0)" onclick="addUser()" style="float: right;margin-right: 10%;color:red;">添加用户 <i class="fa fa-plus"></i></a></div>
			<div class="cnt">
				<div class="tabwrap">
					<!--表格-->
					<table class="tztab">
						<caption>
							<div class="fr sbtn">
								<input type="text" id="keywords" class="fl" placeholder="搜索的关键字..."/><a href="javascript:void(0);" onclick="wpAdmin.search(this);" class="fl"><i class="fa fa-search "></i></a>
							</div>
						</caption>
						<!--头部
						<colgroup id="days">
							<col/>
							<col style="background:green;width:200px;"/>
							<col id="t"/>
						</colgroup>-->
						<thead>
							<tr>
								<th>主键</th>
								<th>用户名</th>	
								<th>邮箱</th>	
								<th>手机</th>	
								<th>创建时间</th>	
								<th>操作</th>
							</tr>
						</thead>
						<!--身体部-->
						<tbody id="tbody" data-model="user">
							
						</tbody>
					</table>
					<div class="cpage"></div>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript">
	
		$(function(){
	
			wpAdmin.loadData(0,10,function(itemCount){
				wpAdmin.initPage(itemCount);//分页加载一次吗
			});
			
			$(".nav").find("li.items").find("a").click(function(){
				var len = $(this).next().length;
				if(len>0){
					$(this).next().stop(true,true).slideToggle()
					.end().parents("li")
					.addClass("active")
					.siblings().removeClass("active")
					.find("ul").slideUp("slow");
				}
			});
		});
		function addUser(){
			layer.open({
				  type: 1,
				  title:"添加链接",
				  skin: 'layui-layer-demo', //样式类名
				  closeBtn: 1, //不显示关闭按钮
				  anim: 2,
				  area:['250px','300px'],
				  shadeClose: true, //开启遮罩关闭
				  content: "<p><input type=\"text\" name=\"name\" placeholder=\"请输入用户名\"></p>"+
					  "<p><input type=\"email\" name=\"email\" placeholder=\"请输入邮箱\"></p>"+
					  "<p><input type=\"password\" name=\"pwd\" placeholder=\"请输入密码\"></p>"+
					  "<p><input type=\"password\" name=\"pwd2\" placeholder=\"确认密码\"></p>	"+
					  "<p><input type=\"text\" name=\"phone\" placeholder=\"手机号码\"></p>",
				  btn:["确定","取消"],
				  yes:function(index){
					  var name = $("input[name='name']").val();
					  var email = $("input[name='email']").val();
					  var pwd = $("input[name='pwd']").val();
					  var pwd2 = $("input[name='pwd2']").val();
					  var phone = $("input[name='phone']").val();
					  if(isEmpty(name)){
						  layer.msg("请输入用户名",{time:2000});
						  return;
					  }else{
						  if(nameExist(name)){
							  layer.msg("用户名已存在请重新输入",{time:2000});
							  return;
						  }
					  }
					  if(isEmpty(email)){
						  layer.msg("请输入邮箱",{time:2000});
						  return;
					  }
					  if(!isEmail(email)){
						  layer.msg("请输入正确的邮箱",{time:2000});
						  return;
					  }else{
						  if(emailExist(email)){
							  layer.msg("邮箱已被使用、请更换邮箱",{time:2000});
							  return;
						  }
					  }
					  if(isEmpty(pwd)){
						  layer.msg("请输入密码",{time:2000});
						  return;
					  }
					  if(isEmpty(pwd2)){
						  layer.msg("请输入确认密码",{time:2000});
						  return;
					  }
					  if(pwd != pwd2){
						  layer.msg("密码不一致请重新输入",{time:2000});
						  $("input[name='pwd2']").val("");
						  $("input[name='pwd2']").focus();
						  return;
					  }
					  var data = {
							  username:name,
							  email:email,
							  password:pwd,
							  telephone:phone
					  }
					  wpAjax.request({
						  path:"${adminPath}",
						  model:"user",
						  method:"saveUser",
						  success:function(data){
							  if(data != -1){
								  layer.msg("添加成功",{time:2000},function(){
									  $("#tbody").append("<tr>"+
											  "<td>"+data+""+
											  "</td>"+
											  "<td>"+name+"</td>"+
											  "<td>"+email+"</td>"+
											  "<td>"+phone+"</td>"+
											  "<td class=\"tmui-tips\" data-tip=\""+getTime()+"\">"+
											  "1秒前</td>"+
											  "<td><a href=\"javascript:void(0);\" data-opid=\"1\" data-state=\"0\" data-mark=\"forbid\" onclick=\"wpAdmin.updateUser(this)\">禁止</a>&nbsp;&nbsp;&nbsp;<a href=\"javascript:void(0);\" data-opid=\"1\" data-state=\"1\" data-mark=\"del\" onclick=\"wpAdmin.updateUser(this)\">删除</a>&nbsp;&nbsp;&nbsp;<a href=\"javascript:void(0);\" data-opid=\"1\" data-mark=\"update\" onclick=\"wpAdmin.updateUser(this)\">修改</a></td>"+
											  "</tr>");
									  layer.close(index);
								  });
							  }else{
								  layer.msg("添加失败",{time:2000});
							  }
						  }
					  },data)
					  
				  }
			});
		}
		function updateUser(obj){
			var hname  = $(obj).parent().siblings()[1].innerText;
			var hemail = $(obj).parent().siblings()[2].innerText;
			var hphone = $(obj).parent().siblings()[3].innerText;
			
			layer.open({
				  type: 1,
				  title:"添加链接",
				  skin: 'layui-layer-demo', //样式类名
				  closeBtn: 1, //不显示关闭按钮
				  anim: 2,
				  area:['250px','300px'],
				  shadeClose: true, //开启遮罩关闭
				  content: "<p><input type=\"text\" name=\"name\" value="+hname+" placeholder=\"请输入用户名\"></p>"+
					  "<p><input type=\"email\" name=\"email\" value="+hemail+" placeholder=\"请输入邮箱\"></p>"+
					  "<p><input type=\"password\" name=\"pwd\" disabled=\"disabled\" placeholder=\"请输入原密码\"></p>"+
					  "<p><input type=\"password\" name=\"pwd2\" placeholder=\"请输入新密码\"></p>	"+
					  "<p><input type=\"text\" name=\"phone\" value="+hphone+" placeholder=\"手机号码\"></p>",
				  btn:["确定","取消"],
				  yes:function(index){
					  var name = $("input[name='name']").val();
					  var email = $("input[name='email']").val();
					  var pwd = $("input[name='pwd']").val();
					  var pwd2 = $("input[name='pwd2']").val();
					  var phone = $("input[name='phone']").val();
					  if(isEmpty(name)){
						  layer.msg("请输入用户名",{time:2000});
						  return;
					  }else if(!name == hname){
						  if(nameExist(name)){
							  layer.msg("用户名已存在请重新输入",{time:2000});
							  return;
						  }
					  }
					  if(isEmpty(email)){
						  layer.msg("请输入邮箱",{time:2000});
						  return;
					  }
					  if(!isEmail(email)){
						  layer.msg("请输入正确的邮箱",{time:2000});
						  return;
					  }else if(!email == hemail){
						  if(emailExist(email)){
							  layer.msg("邮箱已被使用、请更换邮箱",{time:2000});
							  return;
						  }
					  }
					  /* if(isEmpty(pwd)){
						  layer.msg("请输入密码",{time:2000});
						  return;
					  } */
					  if(isEmpty(pwd2)){
						  layer.msg("请输入确认密码",{time:2000});
						  return;
					  }
					 
					  var data = {
							  id:$(obj).data("opid"),
							  username:name,
							  email:email,
							  //opwd:pwd,
							  password:pwd2,
							  telephone:phone
					  }
					  wpAjax.request({
						  path:"${adminPath}",
						  model:"user",
						  method:"update",
						  success:function(data){
							  if(data == "success"){
								  layer.msg("西修改成功",{time:2000},function(){
									 
									  layer.close(index);
								  });
							  }else{
								  layer.msg("修改失败",{time:2000});
							  }
						  }
					  },data)
					  
				  }
			});
		}
		
		function nameExist(name){
			var flag;
			wpAjax.request({
				  path:"${adminPath}",
				  model:"user",
				  method:"isName",
				  async: false,
				  success:function(data){
					  var data = data.trim();
					  if(data == "yes"){
						  flag = true;
					  }else{
						  flag = false;
					  }
				  }
			  }, {"name":name})
			return flag;
		}
		
		function emailExist(email){
			var flag = false;
			wpAjax.request({
				  path:"${adminPath}",
				  model:"user",
				  method:"isEmail",
				  async:false,
				  success:function(data){
					  var data = data.trim();
					  if(data == "yes"){
						  flag = true;
					  }
				  }
			  }, {"email":email})
			  
			  return flag;
		}
		
	</script>
 </body>
</html>


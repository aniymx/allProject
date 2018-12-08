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
	<style type="text/css">
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
			<div class="channel"> 位置 > 内容管理  <a href="javascript:void(0)" onclick="addRole()" style="float: right;margin-right: 10%;color:red;">添加角色 <i class="fa fa-plus"></i></a></div>
			<div class="cnt">
				<div class="tabwrap">
					<!--表格-->
					<table class="tztab">
						<caption>
							<div class="fr sbtn">
								<input type="text" id="keywords" class="fl" placeholder="搜索的关键字..."/><a href="javascript:void(0);" onclick="wpAdmin.search(this);" class="fl"><i class="fa fa-search "></i></a>
							</div>
						</caption>
						<thead>
							<tr>
								<th>主键</th>
								<th>角色名称</th>
								<!-- <th>用户</th> -->
								<th>创建时间</th>
								<th>删除状态</th>
								<th>操作</th>
							</tr>
						</thead>
						<!--身体部-->
						<tbody id="tbody" data-model="role">
							
						</tbody>
					</table>
					<div class="cpage"></div>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript">
	var wpRole = {
			user : function(obj) {
				var $obj = $(obj);
				var id = $obj.data("opid");
				layer.open({
					type : 2,
					title : '分配用户角色',
					shadeClose : true,
					shade : false,
					maxmin : true, //开启最大化最小化按钮
					area : [ '400px', '300px' ],
					content : 'user/'+id,
					success : function() {
						$(".layui-layer ").append('<div class="layui-layer-btn layui-layer-btn-" style="background-color: #ffffff;border-radius: 2px 2px 0 0;border: 1px solid rgba(0,0,0,.1);box-shadow: 1px 1px 5px rgba(0,0,0,.2);border-top: 1px solid #eee;"><a class="layui-layer-btn0 sure">确定</a><a class="layui-layer-btn1 cancel">取消</a></div>');
						$(".cancel").click(function(){
							$(".layui-layer-close").click();
						});
						$(".sure").click(function(){
							document.querySelector("iframe").contentWindow.wpUserRole.save(id);
						});
						
					}
				})
	
			},
			permission : function(obj) {
				var $obj = $(obj);
				var id = $obj.data("opid");
				layer.open({
					type : 2,
					title : '分配用户权限',
					shadeClose : true,
					shade : false,
					maxmin : true, //开启最大化最小化按钮
					area : [ '400px', '500px' ],
					content : 'permission',
					success : function() {
						
						$(".layui-layer ").append('<div class="layui-layer-btn layui-layer-btn-" style="background-color: #ffffff;border-radius: 2px 2px 0 0;border: 1px solid rgba(0,0,0,.1);box-shadow: 1px 1px 5px rgba(0,0,0,.2);border-top: 1px solid #eee;"><a class="layui-layer-btn0 sure">确定</a><a class="layui-layer-btn1 cancel">取消</a></div>');
						$(".cancel").click(function(){
							$(".layui-layer-close").click();
						});
						var pd = document.querySelector("iframe").contentWindow.wpPermissionRole;
						pd.checked(id);
						$(".sure").click(function(){
							var treeObj = pd.treeObj;
							var nodes = treeObj.getCheckedNodes();
							var arr = [];
							nodes.forEach(function(val){
								arr.push(val.id);
							})
							wpAjax.request({
								path:adminPath,
								model:"role",
								method:"saveRolePermission",
								success:function(data){
									if(data == "success"){
										layer.msg('权限分配成功', {icon: 1,time: 2000}); 
									}
									if(data == "fail"){
										loading("权限分配失败",4);
									}
								}
							}, {permissions:arr.toString(),rid:id});
						});
					}
				})
			},
			del : function() {}
		}
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
		function addRole(){
			
			layer.open({
				  type: 1,
				  title:"添加角色",
				  skin: 'layui-layer-demo', //样式类名
				  closeBtn: 1, //不显示关闭按钮
				  anim: 2,
				  area:['250px','200px'],
				  shadeClose: true, //开启遮罩关闭
				  content: "<p><input type=\"text\" name=\"name\" placeholder=\"请输入角色名称\"></p>"+
					  "<p><input type=\"text\" name=\"description\" placeholder=\"角色描述\"></p>",
				  btn:["确定","取消"],
				  yes:function(index){
					  var name = $("input[name='name']").val();
					  var description = $("input[name='description']").val()
					  
					  if(isEmpty(name)){
						  layer.msg("请输入 角色名称",{time:2000});
					      return;
					  }
					  if(isEmpty(description)){
						  layer.msg("请输入 角色描述",{time:2000});
					      return;
					  }
					  wpAjax.request({
						  path:"${adminPath}",
						  model:"role",
						  method:"saveRole",
						  success:function(data){
							  alert(data);
							  if(data != -1){
								  $("#tbody").append("<tr>"+
										  "<td>"+data+"</td>"+
										  "<td>"+name+"</td>"+
										  "<td class=\"tmui-tips\" data-tip=\""+new Date()+"\">"+
										  "1秒前</td>"+
										  "<td>"+
										  "<a href=\"javascript:void(0)\" class=\"green\" data-mark=\"isDelete\" data-opid=\""+data+"\" data-val=\"0\" onclick=\"wpAdmin.op(this)\">未删除</a></td>"+
										  "<td><a href=\"javascript:void(0);\" data-opid=\""+data+"\" onclick=\"wpRole.user(this)\">分配用户</a>&nbsp;&nbsp;&nbsp;<a href=\"javascript:void(0);\" data-opid=\""+data+"\" onclick=\"wpRole.permission(this)\">分配权限</a></td>"+
										  "</tr>");
							  }
						  }
						  
					  }, {name:name,description:description})
				  } 
			});
			
		}
	</script>
 </body>
</html>


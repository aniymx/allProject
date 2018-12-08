<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@include file="/WEB-INF/pages/common/taglib.jsp"%>
<!DOCTYPE HTML>
<html>
  <head>
    <title>头部管理</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" href="${rPath}/css/tz-page.css">
	<%@include file="/WEB-INF/pages/admin/common/common.jsp" %>
	<style>
		body{
			overflow-x:hidden;
		}
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
			<div class="channel"> 位置 > 友链管理  <a href="javascript:void(0)" onclick="addLink()" style="float: right;margin-right: 10%;color:red;">添加友链 <i class="fa fa-plus"></i></a></div>
			<div class="cnt">
				<div class="tabwrap">
					<!--表格-->
					<table class="tztab">
						<thead>
							<tr>
								<!-- <th>主键</th> -->
								<th>名称</th>	
								<th>地址</th>	
								<th>创建时间</th>	
								<th>操作</th>
							</tr>
						</thead>
						<!--身体部-->
						<tbody id="tbody" data-model="content">
							<c:forEach items="${links}" var="link">
								<tr>
									<%-- <td>${link.id}</td> --%>
									<td class="l_name">${link.name}</td>	
									<td class="l_link"><a href="${link.link}" target="_blank">${link.link}</a></td>	
									<td>${wp:formatDate(link.createTime,'yyyy-MM-dd mm:hh:ss')}【${wp:timeFormat(link.createTime)}】</td>	
									<td><a href="javascript:void(0)" onclick="update(this)" data-id="${link.id}">修改</a>&nbsp;&nbsp;<a href="javascript:void(0)" onclick="del(this)" data-id="${link.id}" >删除</a></th>
								</tr>
							</c:forEach>
							
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript">
		function addLink(){
			layer.open({
				  type: 1,
				  title:"添加链接",
				  skin: 'layui-layer-demo', //样式类名
				  closeBtn: 1, //不显示关闭按钮
				  anim: 2,
				  area:['250px','200px'],
				  shadeClose: true, //开启遮罩关闭
				  content: '<p><input type="text" name="name" placeholder="请输入网站名称" /></p><p><input type="text" name="address" placeholder="请输入网站地址(带协议)" /></p>',
				  btn:["确定","取消"],
				  yes:function(index){
					  var name = $("input[name='name']").val();
					  var link = $("input[name='address']").val();
					  if(isEmpty(name)){
						  layer.msg("请输入网站名称",{time:2000});
						  return;
					  }
					  if(isEmpty(link)){
						  layer.msg("请输入网站地址",{time:2000});
						  return;
					  }
					  if(!checkUrl(link)){
						  layer.msg("请输入正确地址(带协议)",{time:2000});
						  return;
					  }
					  
					  wpAjax.request({
						  path:"${adminPath}",
						  model:"other",
						  method:"saveLink",
						  success:function(data){
							  if(data != -1){
								  layer.msg("添加成功",{time:2000},function(){
									  $("#tbody").append("<tr>"+
											  "<td>"+name+"</td>	"+
											  "<td><a href='"+link+"' target=\"_blank\">"+link+"</a></td>	"+
											  "<td>"+getTime()+"【1秒前】</td>	"+
											  "<td><a href=\"javascript:void(0)\" onclick=\"update(this)\" data-id=\""+data+"\">修改</a>&nbsp;&nbsp;<a href=\"javascript:void(0)\" onclick=\"del(this)\" data-id=\""+data+"\" >删除</a>"+
											  "</td></tr>");
									  layer.close(index);
								  });
							  }else{
								  layer.msg("添加失败",{time:2000});
							  }
						  }
					  }, {"name":name,"link":link})
					  
				  }
			});
		}
		function update(obj){
			var $this = $(obj).parent();
			var hname = $this.siblings(".l_name").text();
			var hlink = $this.siblings(".l_link").text();
			layer.open({
				  type: 1,
				  title:"修改链接",
				  skin: 'layui-layer-demo', //样式类名
				  closeBtn: 1, //不显示关闭按钮
				  anim: 2,
				  area:['250px','200px'],
				  shadeClose: true, //开启遮罩关闭
				  content: '<p><input type="text" name="name" value="'+hname+'" placeholder="请输入网站名称" /></p><p><input type="text" name="address" value="'+hlink+'" placeholder="请输入网站地址(带协议)" /></p>',
				  btn:["确定","取消"],
				  yes:function(index){
					  var name = $("input[name='name']").val();
					  var link = $("input[name='address']").val();
					  if(isEmpty(name)){
						  layer.msg("请输入网站名称",{time:2000});
						  return;
					  }
					  if(isEmpty(link)){
						  layer.msg("请输入网站地址",{time:2000});
						  return;
					  }
					  wpAjax.request({
						  path:"${adminPath}",
						  model:"other",
						  method:"updateLink",
						  success:function(data){
							  var data = data.trim();
							  if(data == "success"){
								  layer.msg("修改成功",{time:2000},function(){
									  $this.siblings(".l_name").text(name);
									  $this.siblings(".l_link").find("a").text(link);
									  layer.close(index);
								  });
							  }else{
								  layer.msg("修改失败",{time:2000});
							  }
						  }
					  }, {"id":$(obj).data('id'),"name":name,"link":link})
					  
				  }
			});
			
		}
		function del(obj){
			
			layer.confirm('你确定要删除吗？', {
			  btn: ['确定','取消'] //按钮
			}, function(index){
				
				wpAjax.request({
					  path:"${adminPath}",
					  model:"other",
					  method:"delLink",
					  success:function(data){
						  var data = data.trim();
						  if(data == "success"){
							$(obj).parent().parent().remove();
							  layer.msg("删除成功",{time:2000},function(){
								  layer.close(index);
							  });
						  }else{
							  layer.msg("修改失败",{time:2000});
						  }
					  }
				  }, {"id":$(obj).data('id')})
	
			}, function(){
			  	
			});
			
			
		}
	</script>
 </body>
</html>


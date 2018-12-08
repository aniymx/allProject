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
	<script type="text/javascript" src="http://localhost/blog/edit/ueditor.config.js"></script>
    <script type="text/javascript" src="http://localhost/blog/edit/ueditor.all.min.js"> </script>
    <script type="text/javascript" src="http://localhost/blog/edit/lang/zh-cn/zh-cn.js"></script>
	<style>
		body{
			overflow-x:hidden;
		}
		.content p input,textarea{
			width: 100%;
		    outline: none;
		    text-indent: 10px;
		    height: 36px;
		    font-size: 20px;
		    color: #502828;
		    margin: 10px 0 10px 0;
		}
		.content p textarea {
			height:200px;
		}
		.save a{
    		font-size: 20px;
		    color: red;
		    display: inline-block;
		    width: 100%;
		    height: 36px;
		    line-height: 36px;
		    text-align: center;
		    border: 1px solid #ccc;
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
			<div class="channel"> 位置 > 头部管理</div>
			<p>
				<input type="button" value="点击选着或上传一张背景图([>1400]*210)" onclick="openDialog()"/>
				<img id="img" src="${top.url}" value="${top.url}" />
			</p>
			<p>
				<input type="text" value="${top.title}" id="title" />
			</p>
			<p>
				<textarea id="con" maxlength="300" data-id="${top.id}" >${top.content}</textarea> 
			</p>
			<p class="save"><a href="javascript:void(0)" onclick="save()" >保存修改</a></p>
			
			
		</div>
	</div>
	<script id="ban" type="text/plain" style="width:1210px;height:150px"></script>
	<script type="text/javascript">
		var _editor = UE.getEditor("ban");
		_editor.ready(function () {
	        //设置编辑器不可用
	        //_editor.setDisabled();  这个地方要注意 一定要屏蔽
	        //隐藏编辑器，因为不会用到这个编辑器实例，所以要隐藏
	        _editor.hide();
	        //侦听图片上传
	        _editor.addListener('beforeinsertimage', function (t, arg) {
	            //将地址赋值给相应的input,只去第一张图片的路径
	            var imgs;
	            for(var a in arg){
	                imgs +=arg[a].src+',';
	            }
	           $("#img").attr("value", arg[0].src);
	            //图片预览
	           $("#img").attr("src", arg[0].src);
	        })

	    });

		function openDialog(){
			_editor.getDialog("insertimage").open();
		}
		function save(){
			var data = {
				id:$("#con").data("id"),
				url:$("#img").attr("value"),
				title:$("#title").val(),
				content:$("#con").val()
			}
			wpAjax.request({
				path:"${adminPath}",
				model:"other",
				method:"saveTop",
				success:function(data){
					var data = data.trim();
					if(data == "success"){
						layer.msg("保存成功");
					}else if(data == "fail"){
						layer.msg("保存失败");
					}else{
						layer.msg("系统出错、请稍后再试！");
					}
				}
			},data);
		}
	</script>
 </body>
</html>


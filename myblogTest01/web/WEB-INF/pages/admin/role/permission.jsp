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
	<link rel="stylesheet" href="${rPath}/zTree/css/zTreeStyle.css">
	<%@include file="/WEB-INF/pages/admin/common/common.jsp"%>
	<script type="text/javascript" src="${rPath}/zTree/jquery.ztree.all.min.js"></script>
</head>
<body>
	<div id="container">
		<ul id="ztree" class="ztree">
			
		</ul>
	</div>
	<script type="text/javascript">
		var wpPermissionRole = {
				treeObj:"",
				init:function(){
					wpAjax.request({
						path:adminPath,
						model:"permission",
						method:"root",
						success:function(data){
							var setting = {
									check: {
										enable: true
									},
									data: {
										simpleData: {
											enable: true
										}
									}
									
								};
							//wpPermissionRole.treeObj = $.fn.zTree.init($("#ztree"), setting, data);
							
							$.fn.zTree.init($("#ztree"), setting, data);
							
							wpPermissionRole.treeObj = $.fn.zTree.init($("#ztree"), setting, data)
						},
						
					},{})
				},
				checked:function(rid){
					var params = {};
					wpAjax.request({
						path:adminPath,
						model:"permission",
						method:"root",
						success:function(data){
							var setting = {
									check: {
										enable: true
									},
									data: {
										simpleData: {
											enable: true
										}
									}
									
								};
							params.setting = setting;
							params.data = data;
							wpPermissionRole.treeObj = $.fn.zTree.init($("#ztree"), setting, data);
							
							wpAjax.request({
								path:adminPath,
								model:"role",
								method:"permissionData",
								success:function(data2){
									
									//var nodes = wpPermissionRole.treeObj.getNodes();
									var treeObj = wpPermissionRole.treeObj;
									//alert(JSON.stringify(data));
									data2.forEach(function(obj){
										if(treeObj.getNodeByParam("id",obj.id)){
											if(treeObj.getNodeByParam("id",obj.id).children != null){
												
											}else{
												treeObj.checkNode(treeObj.getNodeByParam("id",obj.id),true,true);
											}
										}
									})
								}
							},{rid:rid})
							
						}
						
					},{})
					
				},
				
		}
		//wpPermissionRole.init();
		
	</script>
</body>
</html>
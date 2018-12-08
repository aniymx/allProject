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
	<link rel="stylesheet" href="${rPath}/css/tz-page.css">
	<%@include file="/WEB-INF/pages/admin/common/common.jsp" %>
	<script type="text/javascript" src="${rPath}/js/tz_page.js"></script>
	<script type="text/javascript" src="${rPath}/js/echarts.min.js"></script>
 </head>
 <body>
	<div class="wrap">
		<!-- left页面 -->
		<%@include file="/WEB-INF/pages/admin/common/left.jsp" %>
		<div class="content">
			<!-- 头部页面 -->
			<%@include file="/WEB-INF/pages/admin/common/header.jsp" %>
			<div class="channel"> 位置 > 日志管理</div>
			<div class="cnt">
				<div id="main" style="height:500px"></div>
			</div>
			<!--表格-->
			<table class="tztab">
				<thead>
					<tr>
						<th>主键</th>
						<th>标题 <span class="up"></span> <span class="down"></span></th>	
						<th>创建时间</th>	
						<th>操作</th>
					</tr>
				</thead>
				<!--身体部-->
				<tbody id="tbody">
					
				</tbody>
			</table>
			<div class="cpage"></div>
		</div>
	</div>
	<script type="text/javascript">
	
		$(function(){
	
			loadData();
		});
		function loadData(){
			var result = [];
			$.ajax({
				type:'post',
				url:adminPath+"/log/conlist",
				data:{logYear:2017},
				success:function(data){
					result.push(parseData(data));
					console.log(result)
					shopCharts('main',['2017'],result);
					result = [];
				}
			});
			
		}
		//数据月份补零
		function parseData(jsonArr){
			var jsonObj = {};
			for(var key in jsonArr){
				var num = jsonArr[key].num;
				var m = jsonArr[key].m;
				jsonObj["s"+parseInt(m)]= num;
			};
			var arr = [];
			for(var i=1;i<=12;i++){
				arr.push(jsonObj["s"+i]||0);
			}
			return arr;
		};
		function shopCharts(target,dataTitle,data){
			var myChart = echarts.init(document.getElementById(target),10,10);
			var option = {
				    title : {
				        text: '网站内容统计',
				        subtext: "总数/月份"
				    },
				    tooltip : {
				        trigger: 'axis'
				    },
				    legend: {
				        data:dataTitle
				    },
				    toolbox: {
				        show : true,
				        feature : {
				            dataView : {show: true, readOnly: false},
				            magicType : {show: true, type: ['line', 'bar']},
				            restore : {show: true},
				            saveAsImage : {show: true}
				        }
				    },
				    calculable : true,
				    xAxis : [
				        {
				            type : 'category',
				            data : ['1月','2月','3月','4月','5月','6月','7月','8月','9月','10月','11月','12月']
				        }
				    ],
				    yAxis : [
				        {
				            type : 'value'
				        }
				    ],
				    series : 
					        {
					            name:dataTitle,
					            type:'bar',
					            data:data[0],
					            markPoint : {
					                data : [
					                    {type : 'max', name: '最大值'},
					                    {type : 'min', name: '最小值'}
					                ]
					            },
					            markLine : {
					                data : [
					                    {type : 'average', name: '平均值'}
					                ]
					            }
					        }
					  
				    
				};
			myChart.setOption(option);
			myChart.on('click', function (params) {
				loadContentByMonthod(params,0,10,function(count){
					$(".cpage").tzPage(count, {
						num_edge_entries : 1, //边缘页数
						num_display_entries : 4, //主体页数
						num_edge_entries : 5,
						current_page : 0,
						showGo : true,
						showSelect : true,
						items_per_page : 10, //每页显示X项
						prev_text : "前一页",
						next_text : "后一页",
						callback : function(pageNo, psize) { //回调函数
							loadContentByMonthod(params,pageNo, psize);
						}
					});
				});
				
			});
			
		}
		var flag = true;
		function loadContentByMonthod(params,pageNo,pageSize,callback){
			var datas = {logMonth:parseInt(params.name),pageNo : pageNo * pageSize,pageSize : pageSize,};
			if(flag){
				flag = !flag;
				wpAjax.request({
					path:adminPath,
					model:"log",
					method:"conMonthList",
					success:function(data){
						count = data.substring(data.lastIndexOf("]")+1,data.length);
						data = data.substring(0,data.lastIndexOf("]")+1);
						data = JSON.parse(data);
						var len = data.length;
						var html = "";
						for(var i=0;i<len;i++){
							html += "<tr>"+
									"<td>"+data[i].id+"</td>"+
									"<td>"+data[i].title+"</td>"+
									"<td>"+new Date(data[i].createTime).format('yyyy-MM-dd HH:mm:ss')+"</td>"+
									"<td>操作</td>"+
							"</tr>";	
						}
						if(callback)callback(count);
						$("#tbody").html(html);
						flag = true;
					}
				}, datas)
			}
		}
	</script>
 </body>
</html>


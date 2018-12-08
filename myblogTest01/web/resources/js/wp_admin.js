
var wpAdmin = {
	timer : null,
	initPage : function(itemCount) {
		$(".cpage").tzPage(itemCount, {
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
				wpAdmin.loadData(pageNo, psize);
			}
		});
	},
	loadData : function(pageNo, pageSize, callback) {
		var keyword = $("#keywords").val();
		var model = $("#tbody").data("model");
		var $this = this;
		clearTimeout(this.timer);
		this.timer = setTimeout(function() {
			$.ajax({
				type : "post",
				beforeSend : function() {},
				url : adminPath + "/" + model + "/template",
				data : {
					pageNo : pageNo * pageSize,
					pageSize : pageSize,
					keyword : keyword
				},
				success : function(data) {
					if (data == "logout") {
						//第一种方案：登陆弹出框
						//第二种方案：直接跳转
						window.location.href = adminPath + "/login";
					} 
					var $data = $(data);
					$("#tbody").html($data);
					var itemCount = $data.find("#itemCount").val();
					if (isNotEmpty(keyword)) $this.highlight(keyword);
					if (callback) callback(itemCount);
					var tipdom;
					$(".tmui-tips").hover(function(){
						tipdom = layer.tips($(this).data("tip"), $(this));
					},function(){
						layer.close(tipdom);
					});
				}
			});
		}, 200);
	},
	highlight : function(key) {
		$("#tbody").find(".keys").each(function() {
			var text = $(this).text();
			var regex = new RegExp(key, "ig");
			var nt = text.replace(regex, "<span class='red'>" + key + "</span>");
			$(this).html(nt);
		});
	},
	search : function() {
		wpAdmin.loadData(0, 10, function(itemCount) {
			wpAdmin.initPage(itemCount); //分页加载一次吗
		});
	},
	op : function(obj) {
		var $this = $(obj);
		var opid = $this.data('opid');
		var val = $this.data('val');
		var mark = $this.data('mark');
		var model = $("#tbody").data("model");
		var params = {
			id : opid
		};
		params[mark] = val;
		var markText = {
			"isTop" : [ "置顶", "未置顶" ],
			"isDelete" : ["删除","未删除"],
			"push" : [ "是", "否" ],
			"isComment" : [ "允许", "不允许" ],
			"status" : [ "发布", "未发布" ]
		}
		var text = markText[mark];
		$.ajax({
			type : "post",
			url : adminPath + "/" + model + "/update",
			data : params,
			success : function(data) {
				if(mark == "isDelete"){
					$this.data("val", val == 1 ? 0 : 1).removeClass().addClass(val == 1 ? 'red' : 'green');
					$this.text(val == 1 ? text[0] : text[1]);
				}else{
					$this.data("val", val == 1 ? 0 : 1).removeClass().addClass(val == 1 ? 'green' : 'red');
					$this.text(val == 1 ? text[0] : text[1]);
				}

			}
		});
	},
	delUser:function(obj){
		var $this = $(obj);
		var opid = $this.data('opid');
		var model = $("#tbody").data("model");
		$.ajax({
			type : "post",
			url : adminPath + "/" + model + "/del",
			data : {id : opid},
			success : function(data) {
				$this.parents("tr").find(".delstate").removeClass("green").addClass("red").text("删除");
				loading("删除成功",3)
			}
		});
	},
	staticHtml:function(obj){
		var $this = $(obj);
		var opid = $this.data('opid');
		var cid = $this.data("cid");
		wpAjax.request({
			path : adminPath,
			model : "static",
			method : "html/"+opid,
			success:function(data){
				if(data == "success"){
					layer.msg("静态化页面成功");
				}else{
					layer.msg("静态化页面失败");
				}
			}
		}, {"cid":cid});
	},
	updateUser:function(obj){
		var $this = $(obj);
		var opid = $this.data('opid');
		var mark = $this.data("mark");
		var state = $this.data("state");
		wpAjax.request({
			path : adminPath,
			model : "user",
			method : mark+"/"+opid,
			success:function(data){
				if(data == "success"){
					layer.msg('操作成功');
				}else{
					layer.msg('操作失败');
				}
			}
		}, {"state":state});
	}
}

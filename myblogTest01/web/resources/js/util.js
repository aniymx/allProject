var wpAjax = {
	request : function(options, params) {
		var opts = $.extend({}, {
			path : "",
			type : "post",
			model : "",
			method : "",
			params : "",
			async: true,
			before : function() {
				
			},
			error : function() {},
			success : function() {},
			logout : function() {}
		}, options);
		if (!opts.url) {
			opts.url = opts.path + "/" + opts.model + "/" + opts.method + (opts.params ? "?" + opts.params : "");
		}
		$.ajax({
			type : opts.type,
			url : opts.url,
			beforeSend : opts.before,
			error : opts.error,
			data : params,
			async:opts.async,
			success : function(data) {
				if (data == "logout") {
					if (opts.logout) opts.logout();
					//第一种方案：登陆弹出框
					//第二种方案：直接跳转
					window.location.href = opts.path + "/login";
				} else {
					if (opts.success) opts.success(data);
				}
			}
		});
	}
}
/**
 * 判断非空
 * 
 * @param val
 * @returns {Boolean}
 */

function isEmpty(val) {
	val = $.trim(val);
	if (val == null)
		return true;
	if (val == undefined || val == 'undefined')
		return true;
	if (val == "")
		return true;
	if (val.length == 0)
		return true;
	if (!/[^(^\s*)|(\s*$)]/.test(val))
		return true;
	return false;
}

function isNotEmpty(val) {
	return !isEmpty(val);
};
function randomColor(){
	var r = Math.floor(Math.random()*256);
	var g = Math.floor(Math.random()*256);
	var b = Math.floor(Math.random()*256);
	return "rgb("+r+","+g+","+b+")";//IE7不支出rgb
}

function randomColor16(){
	//0-255	
	var r = random(255).toString(16);
	var g = random(255).toString(16);
	var b = random(255).toString(16);
	//255的数字转换成十六进制
	if(r.length<2)r = "0"+r;
	if(g.length<2)g = "0"+g;
	if(b.length<2)b = "0"+b;
	return "#"+r+g+b;
}

function random(num){
	return Math.floor(Math.random()*(num+1));
}

function randomRange(start,end){
	return Math.floor(Math.random()*(end-start+1))+start;
};

function isEmail(email) {
	var reg = /^[a-z0-9]+([._\\-]*[a-z0-9])*@([a-z0-9]+[-a-z0-9]*[a-z0-9]+.){1,63}[a-z0-9]+$/;
	return reg.test(email);
}
function isPhone(phone) {
	var reg = /^0?1[3|4|5|8][0-9]\d{8}$/;
	return reg.test(phone);
}
/**
 * 获取距离当前时间多久
 */
function getTimeFormat(startTime){
	var startTimeMills = startTime.getTime();
	var endTimeMills = new Date().getTime();
	var diff = parseInt((endTimeMills - startTimeMills)/1000);//秒
	var day_diff = parseInt(Math.floor(diff/86400));//天
	var buffer = Array();
	if(day_diff<0){
		return "[error],时间越界...";
	}else{
		if(day_diff==0 && diff<60){
			if(diff<=0)diff=1;
			buffer.push(diff+"秒前");
		}else if(day_diff==0 && diff<120){
			buffer.push("1 分钟前");
		}else if(day_diff==0 && diff<3600){
			buffer.push(Math.round(Math.floor(diff/60))+"分钟前");
		}else if(day_diff==0 && diff<7200){
			buffer.push("1小时前");
		}else if(day_diff==0 && diff<86400){
			buffer.push(Math.round(Math.floor(diff/3600))+"小时前");
		}else if(day_diff==1){
			buffer.push("1天前");
		}else if(day_diff<7){
			buffer.push(day_diff+"天前");
		}else if(day_diff <30){
			buffer.push(Math.round(Math.ceil( day_diff / 7 )) + " 星期前");
		}else if(day_diff >=30 && day_diff<=179 ){
			buffer.push(Math.round(Math.ceil( day_diff / 30 )) + "月前");
		}else if(day_diff >=180 && day_diff<365){
			buffer.push("半年前");
		}else if(day_diff>=365){
			buffer.push(Math.round(Math.ceil( day_diff /30/12))+"年前");
		}
	}
	return buffer.toString();
};
function getTime() {
	return new Date().format("yyyy-MM-dd hh:mm:ss");
}
/*判断是否为网站*/
function checkUrl(str) {
    var RegUrl = new RegExp();
    RegUrl.compile("^[A-Za-z]+://[A-Za-z0-9-_]+\\.[A-Za-z0-9-_%&\?\/.=]+$");
    if (!RegUrl.test(str)) {
        return false;
    }
    return true;
}
Date.prototype.format = function(fmt) {
	var o = {
		"M+" : this.getMonth() + 1,
		// 月份
		"d+" : this.getDate(),
		// 日
		"h+" : this.getHours() % 12 == 0 ? 12 : this.getHours() % 12,
		// 小时
		"H+" : this.getHours(),
		// 小时
		"m+" : this.getMinutes(),
		// 分
		"s+" : this.getSeconds(),
		// 秒
		"q+" : Math.floor((this.getMonth() + 3) / 3),
		// 季度
		"S" : this.getMilliseconds()
	// 毫秒
	};
	var week = {
		"0" : "/u65e5",
		"1" : "/u4e00",
		"2" : "/u4e8c",
		"3" : "/u4e09",
		"4" : "/u56db",
		"5" : "/u4e94",
		"6" : "/u516d"
	};
	if (/(y+)/.test(fmt)) {
		fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "")
				.substr(4 - RegExp.$1.length));
	}
	if (/(E+)/.test(fmt)) {
		fmt = fmt
				.replace(
						RegExp.$1,
						((RegExp.$1.length > 1) ? (RegExp.$1.length > 2 ? "/u661f/u671f"
								: "/u5468")
								: "")
								+ week[this.getDay() + ""]);
	}
	for ( var k in o) {
		if (new RegExp("(" + k + ")").test(fmt)) {
			fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k])
					: (("00" + o[k]).substr(("" + o[k]).length)));
		}
	}
	return fmt;
};
/**
 * 阻止事件冒泡
 * 
 * @param e
 */
function stopBubble(e) {
	// 如果提供了事件对象，则这是一个非IE浏览器
	if (e && e.stopPropagation)
		// 因此它支持W3C的stopPropagation()方法
		e.stopPropagation();
	else
		// 否则，我们需要使用IE的方式来取消事件冒泡
		window.event.cancelBubble = true;
};
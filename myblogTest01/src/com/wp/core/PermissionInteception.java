package com.wp.core;

import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.wp.dto.WpConstant;
import com.wp.util.Utils;

/**
 * 
 * 工程名:wp_admin
 * 包名:com.wp.core
 * 类名:PermissionInteception		权限验证处理类
 * 创建人:wenpeng
 * Email:1091654568@qq.com
 * 时间：2017年3月28日-下午11:02:38 
 */
public class PermissionInteception implements HandlerInterceptor{

	@Override
	public boolean preHandle(HttpServletRequest req, HttpServletResponse resp, Object obj) throws Exception {
		List<HashMap<String, Object>> permission = (List<HashMap<String, Object>>) req.getSession().getAttribute(WpConstant.SESSION_USER_PERMISSION);
		if(isPermission(permission,req)){
			return true;
		}else {
			//执行是一个ajax请求还是一个普通请求
			String requestType = req.getHeader("X-Requested-With");
			//如果是ajax请求
			if(Utils.isNotEmpty(requestType) && requestType.equalsIgnoreCase("XMLHttpRequest")){
				resp.getWriter().print("nopermission");
			}else{
				resp.sendRedirect(req.getContextPath()+"/nopermission.jsp");
			}
			//跳转到没有权限的页面
			return false;
		}
	}
	//判断请求的地址是否有权限
	private boolean isPermission(List<HashMap<String, Object>> datas,HttpServletRequest request){
		//获取当前的请求路径，注意它没有http getRequestURL
		String ccurl = request.getRequestURI();
		//解析请求地址
		HashMap<String, String> map = getLinks(ccurl);
		String model = map.get("model");
		String method = map.get("method");
		String url = map.get("url");
		boolean mark = false;
		//如果权限数据不为空
		if(datas!=null && datas.size()>0){
			///开始循环对比权限
			for (HashMap<String, Object> hashMap : datas) {
				//获取权限中的URL
				String curl = String.valueOf(hashMap.get("url"));
				//获取权限中的模块
				String cmodel = String.valueOf(hashMap.get("model"));
				//获取权限中的方法
				String cmethod = String.valueOf(hashMap.get("method"));
				//如果URL不为空，就用URL去匹配
				if(Utils.isNotEmpty(url)){
					if(curl.equalsIgnoreCase(url)){
						mark = true;
						break;
					}
				}else{
					//如果为空就用model和method去匹配
					if(Utils.isNotEmpty(cmodel) && 
							Utils.isNotEmpty(method) && 
						cmodel.equalsIgnoreCase(model) && 
						cmethod.equalsIgnoreCase(method)){
						mark = true;
						break;
					}
				}
			}
		}
		return mark;
	}
	private static HashMap<String, String> getLinks(String url){
		if(Utils.isNotEmpty(url)){
			HashMap<String, String> map = new HashMap<>();
			String[] urls = url.split("/");
			String method = "";
			String model = "";
			String admin = "";
			if(isNumeric(urls[urls.length-1])){
				method = urls[urls.length-2];
				model = urls[urls.length-3];
				admin = urls[urls.length-4];
			}else{
				method = urls[urls.length-1];
				model = urls[urls.length-2];
				admin = urls[urls.length-3];
			}
			if(urls.length==4){
				map.put("url", "/"+model+"/"+method);
			}
			if(urls.length==5){
				map.put("url", "/"+admin+"/"+model+"/"+method);
			}
			map.put("model", model);
			map.put("method", method);
			return map;
		}else{
			return null;
		}
	}
	public static boolean isNumeric(String str){ 
	   Pattern pattern = Pattern.compile("[0-9]*"); 
	   Matcher isNum = pattern.matcher(str);
	   if( !isNum.matches() ){
	       return false; 
	   } 
	   return true; 
	}
	@Override
	public void postHandle(HttpServletRequest res, HttpServletResponse resp, Object handler, ModelAndView ex)
			throws Exception {
		
	}

	@Override
	public void afterCompletion(HttpServletRequest req, HttpServletResponse resp, Object handler, Exception ex)
			throws Exception {
		
	}

}

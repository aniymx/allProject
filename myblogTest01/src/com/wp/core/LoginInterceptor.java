package com.wp.core;

import static com.wp.dto.WpConstant.SESSION_USER;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.wp.bean.User;
import com.wp.util.Utils;

/**
 * 
 * 工程名:blog
 * 包名:com.wp.core
 * 类名:LoginIntercepter	登陆拦截器
 * 创建人:wenpeng
 * Email:1091654568@qq.com
 * 时间：2017年4月4日-下午4:49:18 
 */
public class LoginInterceptor implements HandlerInterceptor{

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		System.out.println("进入拦截器。。");
		User user =(User)request.getSession().getAttribute(SESSION_USER);
		if(user!=null){
			return true;
		}else{
			//执行是一个ajax请求还是一个普通请求
			String requestType = request.getHeader("X-Requested-With");
			//如果是ajax请求
			if(Utils.isNotEmpty(requestType) && requestType.equalsIgnoreCase("XMLHttpRequest")){
				response.getWriter().print("logout");
			}else{
				response.sendRedirect(request.getContextPath()+"/sysmgr/admin/login");
			}
			return false;
		}
	}

	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		System.out.println("请求结束执行的方法..........");
		
	}
	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		System.out.println("响应已经被渲染成功后执行的方法..........");
		
	}

}

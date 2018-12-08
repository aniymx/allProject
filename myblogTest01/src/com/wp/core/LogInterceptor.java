package com.wp.core;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.wp.bean.Log;
import com.wp.bean.User;
import com.wp.service.admin.log.ILogService;
import com.wp.tag.ip.TmIpUtil;

/**
 * 
 * 
 * 工程名:blog
 * 包名:com.wp.core
 * 类名:LogInterceptor	日志拦截器处理
 * 创建人:wenpeng
 * Email:1091654568@qq.com
 * 时间：2017年4月5日-下午8:39:45
 */
@Aspect
public class LogInterceptor implements ServletContextAware{
	private ServletContext application;
	@Autowired
	private ILogService logService;
	//@Around("execution(* com.wp.service.*.*.*(..)) and !execution(* com.wp.service.admin.log.*.*(..)) and !execution(* com.wp.service.user.*.getLoginUser(..))")
	@Around("execution(* com.wp.service.*.*.*(..))")
	public Object aroundAdvice(ProceedingJoinPoint pjp) throws Throwable{
		//方法名
		String methodName = pjp.getSignature().getName();
		//拦截执行的对象
		Object classObj = pjp.getTarget();
		long startTime = System.currentTimeMillis();
		Object object = pjp.proceed();
		long endTime = System.currentTimeMillis();
		Object[] params = pjp.getArgs();
		StringBuilder builder = new StringBuilder("");
		if(params.length>0){
			for (int i = 0; i < params.length; i++) {
				builder.append(String.valueOf(params[i]));
				if(i<params.length-1){
					builder.append(",");
				}
			}
		}
		//执行的类名
		String className = classObj.getClass().getName();
		//返回类型
		String returnType = null;
		if(object!=null){
			returnType = object.getClass().getName();
		}
		try {
			saveLog(className, methodName, endTime-startTime);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("【Service AOP拦截】【Class："+className+"】【Method："+methodName+"】【ReturnType："+returnType+"】【Time："+(endTime-startTime)+"ms】");
		return object;
	}
	private void saveLog(String classname,String methodName,long time){
		Log log = new Log();
		//获取User对象
		User user = (User)application.getAttribute("user_log");
		if (user != null) {
			//获取request对象
			HttpServletRequest request = (HttpServletRequest) application.getAttribute("request_log");
			/*ActionContext ac = ActionContext.getContext();
		HttpServletRequest request = (HttpServletRequest)ac.get(ServletActionContext.HTTP_REQUEST);*/
			//HttpServletRequest request = ServletActionContext.getRequest();
			try {
				log.setClassname(classname);
				log.setUserId(user.getId());
				log.setMethod(methodName);
				log.setTime(time);
				log.setIp(TmIpUtil.getIpAddress(request));
				log.setIpAddress(TmIpUtil.ipLocation(request));
				log.setUsername(user.getUsername());
				log.setModel("content");
				log.setDescription("content");
				logService.saveLog(log);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else{
			//获取request
			ServletRequestAttributes attr = (ServletRequestAttributes)RequestContextHolder.currentRequestAttributes(); 
	        HttpServletRequest request = attr.getRequest();
	        
	        user = new User();
	        user.setId(1);
	        user.setUsername("前台访问");
	        try {
				log.setClassname(classname);
				log.setUserId(user.getId());
				log.setMethod(methodName);
				log.setTime(time);
				log.setIp(TmIpUtil.getIpAddress(request));
				log.setIpAddress(TmIpUtil.ipLocation(request));
				log.setUsername(user.getUsername());
				log.setModel("content");
				log.setDescription("content");
				logService.saveLog(log);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void setServletContext(ServletContext application) {
		this.application = application;
	}
}

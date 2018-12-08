package com.wp.web.admin;

import static com.wp.dto.WpConstant.SESSION_USER;
import static com.wp.dto.WpConstant.SESSION_USER_PERMISSION;
import static com.wp.dto.WpConstant.SESSION_USER_USERNAME;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wp.bean.User;
import com.wp.bean.WpParams;
import com.wp.service.admin.permission.IPermissionService;
import com.wp.service.admin.role.IRoleService;
import com.wp.service.admin.user.IUserService;
import com.wp.util.Utils;

/**
 * 
 * 工程名:blog
 * 包名:com.wp.web.admin
 * 类名:AdminIndexController	后台主页
 * 创建人:wenpeng
 * Email:1091654568@qq.com
 * 时间：2017年4月1日-下午9:14:07 
 */
@Controller
@RequestMapping("sysmgr/admin")
public class AdminIndexController {
	@Autowired
	private IUserService userService;
	@Autowired
	private IPermissionService permissionService;
	@Autowired
	private IRoleService roleService;
	
	@RequestMapping("index")
	public String index(){
		return "admin/index";
	}
	@RequestMapping("login")
	public String login(){
		return "admin/login";
	}
	@RequestMapping("logout")
	public String logout(HttpSession session){
		session.invalidate();
		return "redirect:login";
	}
	@ResponseBody
	@RequestMapping("logined")
	public String logined(WpParams params,HttpSession session,HttpServletRequest request){
		if (Utils.isNotEmpty(params.getUsername()) || Utils.isNotEmpty(params.getEmail()) 
				|| Utils.isNotEmpty(params.getTelephone())) {
			String code = request.getParameter("code");
			if(Utils.isNotEmpty(code)){
				String codeSession = (String) request.getSession().getAttribute(com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY);
				if(Utils.isNotEmpty(codeSession) && !code.equalsIgnoreCase(codeSession)){
					return "code_error";
				}
			}
			User user = userService.getLoginUser(params);
			if (user != null) {
				if (user.getForbiden() == 0) {
					return "forbided";
				}else {
					List<HashMap<String, Object>> permission = permissionService.findUserPermission(user.getId());
					List<HashMap<String, Object>> roles = roleService.findRolesByUserId(user.getId());
					session.setAttribute(SESSION_USER, user);
					session.setAttribute(SESSION_USER_USERNAME, user.getUsername());
					session.setAttribute(SESSION_USER_PERMISSION, permission);
					session.setAttribute("session_role_name", roles.get(0));
					request.getServletContext().setAttribute("user_log", user);
					request.getServletContext().setAttribute("request_log", request);
					return "success";
				}
			}else{
				return "fail";
			}
			
		}else {
			return "error";
		}
	}
}

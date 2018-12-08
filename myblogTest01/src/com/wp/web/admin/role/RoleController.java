package com.wp.web.admin.role;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.wp.bean.Role;
import com.wp.bean.WpParams;
import com.wp.service.admin.role.IRoleService;
import com.wp.util.Utils;

import jdk.nashorn.internal.ir.RuntimeNode.Request;

/**
 * 
 * 包名:com.wp.web.role
 * 类名:RoleController  角色模块
 * 创建人:wenpeng 
 * Email:1091654568@qq.com
 * 时间：2017年04月02日-下午10:13:58
 */
@RequestMapping("sysmgr/admin/role")
@Controller
public class RoleController {
	@Autowired
	private IRoleService roleService;
	
	/**
	 * 保存角色
	 * @param role
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="saveRole",method=RequestMethod.POST)
	public int saveRole(Role role){
		
		try {
			roleService.addRole(role);
			System.out.println(role.getId());
			int rid = role.getId();
			return rid;
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
		
	}
	
	
	@RequestMapping("list")
	public String list(WpParams params){
		return "admin/role/list";
	}
	/*获取未分配角色的用户*/
	@RequestMapping("user/{rid}")
	public ModelAndView user(@PathVariable("rid") Integer rid,WpParams params){
		ModelAndView modelAndView = new ModelAndView();
		params.setRoleId(rid);;
		List<HashMap<String, Object>> roles = roleService.findFilterUsers(params);
		int count = roleService.countFilterUsers(params);
		modelAndView.addObject("role_users",roles);
		modelAndView.addObject("count",count);
		modelAndView.setViewName("admin/role/user");
		return modelAndView;
	}
	/*显示权限*/
	@RequestMapping("permission")
	public String permission(HttpServletRequest request){
		return "admin/role/permission";
	}
	/*根据角色ID得到权限数据*/
	@ResponseBody
	@RequestMapping("permissionData")
	public List<HashMap<String, Object>> permissionData(HttpServletRequest request){
		String roleId = request.getParameter("rid");
		if (Utils.isNotEmpty(roleId)) {
			Integer rid = Integer.parseInt(roleId);
			List<HashMap<String, Object>> roles = roleService.findFilterPermissions(rid);
			int count = roleService.countFilterPermissions(rid);
			request.getSession().setAttribute("count",count);
			return roles;
		}else {
			return null;
		}
	}
	/*保存角色用户*/
	@ResponseBody
	@RequestMapping("saveRoleUsers")
	public String saveRoleUsers(HttpServletRequest request){
		//获取要分配的用户
		String userparams= request.getParameter("users");
		//获取分配角色的ID
		String roleId = request.getParameter("rid");
		if (Utils.isNotEmpty(userparams) && Utils.isNotEmpty(roleId)) {
			Integer rid = Integer.parseInt(roleId);
			//用逗号分隔要分配的角色
			String[] users = userparams.split(",");
			//循环保存
			for (String string : users) {
				roleService.delRoleUsers(Integer.parseInt(string),rid);
				roleService.saveRoleUsers(Integer.parseInt(string), rid);
			}
			return "success";
		}else {
			return "fail";
		}
		
	}
	/*保存角色权限*/
	@ResponseBody
	@RequestMapping("saveRolePermission")
	public String saveRolePermission(HttpServletRequest request){
		//获取所用勾选的权限
		String pParams= request.getParameter("permissions");
		//权限ID
		String roleId = request.getParameter("rid");
		if (Utils.isNotEmpty(pParams) && Utils.isNotEmpty(roleId)) {
			Integer rid = Integer.parseInt(roleId);
			/*保存前先删除*/
			roleService.delRolePermission(rid);
			/*分隔权限*/
			String[] users = pParams.split(",");
			for (String string : users) {
				roleService.saveRolePermission(rid,Integer.parseInt(string));
			}
			return "success";
		}else {
			return "fail";
		}
		
	}
	@RequestMapping("template")
	public ModelAndView template(WpParams params){
		ModelAndView modelAndView = new ModelAndView();
		List<Role> roles = roleService.findRoles(params);
		int count = roleService.count(params);
		modelAndView.addObject("roles",roles);
		modelAndView.addObject("itemCount",count);
		modelAndView.setViewName("admin/role/template");
		return modelAndView;
	}
	
}


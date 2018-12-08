package com.wp.web.admin.permission;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wp.bean.WpParams;
import com.wp.service.admin.permission.IPermissionService;

/**
 * 
 * 包名:com.wp.web.permission
 * 类名:PermissionController  权限模块
 * 创建人:wenpeng 
 * Email:1091654568@qq.com
 * 时间：2017年04月05日-下午11:51:38
 */
@RequestMapping("sysmgr/admin/permission")
@Controller
public class PermissionController {
	@Autowired
	private IPermissionService permissionService;
	@RequestMapping("list")
	public String list(WpParams params){
		return "admin/permission/list";
	}
	@ResponseBody
	@RequestMapping("root")
	public List<Map<String, Object>> template(WpParams params){
		List<Map<String, Object>> permissions = permissionService.findPermissionRoot(params);
		return permissions;
	}
	
}


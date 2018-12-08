package com.wp.service.admin.role;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wp.bean.Role;
import com.wp.bean.WpParams;
import com.wp.dao.admin.role.IRoleDao;


/**
 * 
 * 包名:com.wp.service.admin.role.impl
 * 类名:IRoleService  
 * 创建人:wenpeng
 * Email:1091654568@qq.com
 * 时间：2017年04月02日-下午10:13:58
 */
@Service
public class RoleService implements IRoleService {
	@Autowired
	private IRoleDao roleDao;
	@Override
	public int addRole(Role role) {
		role.setIsDelete(0);
		return roleDao.addRole(role);
	}
	
	@Override
	public List<Role> findRoles(WpParams params) {
		return roleDao.findRoles(params);
	}
	@Override
	public int count(WpParams params) {
		return roleDao.count(params);
	}
	@Override
	public List<HashMap<String, Object>> findFilterUsers(WpParams params) {
		return roleDao.findFilterUsers(params);
	}
	@Override
	public int countFilterUsers(WpParams params) {
		return roleDao.countFilterUsers(params);
	}
	@Override
	public int saveRoleUsers(Integer userId, Integer roleId) {
		return roleDao.saveRoleUsers(userId, roleId);
	}
	@Override
	public int delRoleUsers(Integer userId, Integer roleId) {
		return roleDao.delRoleUsers(userId, roleId);
	}
	@Override
	public int saveRolePermission(Integer roleId, Integer permissionId) {
		return roleDao.saveRolePermission(roleId, permissionId);
	}
	@Override
	public int delRolePermission(Integer roleId) {
		return roleDao.delRolePermission(roleId);
	}
	@Override
	public List<HashMap<String, Object>> findFilterPermissions(Integer roleId) {
		return roleDao.findFilterPermissions(roleId);
	}
	@Override
	public int countFilterPermissions(Integer roleId) {
		return roleDao.countFilterPermissions(roleId);
	}
	@Override
	public List<HashMap<String, Object>> findRolesByUserId(Integer userId) {
		return roleDao.findRolesByUserId(userId);
	}
	
}


package com.wp.service.admin.role;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.wp.bean.Role;
import com.wp.bean.WpParams;

/**
 * 
 * 
 * 包名:com.wp.service.admin.role 
 * 类名:IRoleService  
 * 创建人:wenpeng
 * Email:1091654568@qq.com
 * 时间：2017年04月02日-下午10:13:58
 */
public interface IRoleService {
	/**
	 * 添加角色
	 * @param role
	 * @return
	 */
	public int addRole(Role role);
	/**
	 * 
	 * 获取类容
	 * com.wp.service.admin.role 
	 * 方法名：findRoles
	 * 创建人：wenpeng
	 * 时间：2017年04月02日-下午10:13:58 
	 * @param params
	 * @return List<Content>
	 * @exception 
	 * @since  1.0.0
	 */
	public List<Role> findRoles(WpParams params);
	/**
	 * 
	 * 获取内容总数
	 * com.wp.service.admin.role 
	 * 方法名：findRoles
	 * 创建人：wenpeng
	 * 时间：2017年04月02日-下午10:13:58 
	 * @param params
	 * @return int
	 * @exception 
	 * @since  1.0.0
	 */
	public int count(WpParams params);
	/**
	 * 
	 * 获取所有没有分配角色的用户
	 * com.wp.dao.admin.role 
	 * 方法名：findFilterUsers
	 * 创建人：wenpeng
	 * 时间：2017年4月5日-下午2:55:25 
	 * @param params
	 * @return List<HashMap<String,Object>>
	 * @exception 
	 * @since  1.0.0
	 */
	public List<HashMap<String, Object>> findFilterUsers(WpParams params);
	/**
	 * 
	 * 获取所有没有分配角色的用户的总数
	 * com.wp.dao.role 
	 * 方法名：countFilterRoles
	 * 创建人：wenpeng
	 * 时间：2017年4月5日-下午2:55:25 
	 * @param params
	 * @return int
	 * @exception 
	 * @since  1.0.0
	 */
	public int countFilterUsers(WpParams params);
	/**
	 * 
	 * 保存分配角色的用户
	 * com.wp.dao.role 
	 * 方法名：saveRoleUsers
	 * 创建人：wenpeng
	 * 时间：2017年4月5日-下午2:55:25 
	 * @param userId
	 * @param roleId
	 * @return int
	 * @exception 
	 * @since  1.0.0
	 */
	public int saveRoleUsers(@Param("userId") Integer userId, @Param("roleId") Integer roleId);
	/**
	 * 
	 * 删除分配角色的用户
	 * com.wp.dao.role 
	 * 方法名：delRoleUsers
	 * 创建人：wenpeng
	 * 时间：2017年4月5日-下午2:55:25 
	 * @param userId
	 * @return int
	 * @exception 
	 * @since  1.0.0
	 */
	public int delRoleUsers(@Param("userId") Integer userId, @Param("roleId") Integer roleId);
	/**
	 * 
	 * 保存角色分配的权限
	 * com.wp.dao.role 
	 * 方法名：saveRolePermission
	 * 创建人：wenpeng
	 * 时间：2017年4月5日-下午2:55:25 
	 * @param roleId
	 * @param permissionId
	 * @return int
	 * @exception 
	 * @since  1.0.0
	 */
	public int saveRolePermission(@Param("roleId") Integer roleId, @Param("permissionId") Integer permissionId);
	/**
	 * 
	 * 删除用户分配的权限
	 * com.wp.dao.role 
	 * 方法名：delRolePermission
	 * 创建人：wenpeng
	 * 时间：2017年4月5日-下午2:55:25 
	 * @param roleId
	 * @return int
	 * @exception 
	 * @since  1.0.0
	 */
	public int delRolePermission(@Param("roleId") Integer roleId);
	/**
	 * 
	 * 查询过滤后的权限
	 * com.wp.dao.role 
	 * 方法名：findFilterPermissions
	 * 创建人：wenpeng
	 * 时间：2017年4月5日-下午2:55:25  
	 * @return List<HashMap<String,Object>>
	 * @exception 
	 * @since  1.0.0
	 */
	public List<HashMap<String, Object>> findFilterPermissions(@Param("roleId") Integer roleId);
	/**
	 * 
	 * 查询过滤后的权限总数
	 * com.wp.dao.role 
	 * 方法名：countFilterPermissions
	 * 创建人：wenpeng
	 * 时间：2017年4月5日-下午2:55:25 
	 * @return List<HashMap<String,Object>>
	 * @exception 
	 * @since  1.0.0
	 */
	public int countFilterPermissions(@Param("roleId") Integer roleId);
	/**
	 * 
	 * 查询用户所拥有的的角色
	 * com.wp.dao.role 
	 * 方法名：findRolesByUserId
	 * 创建人：wenpeng
	 * 时间：2017年4月5日-下午2:55:25  
	 * @param userId
	 * @return List<HashMap<String,Object>>
	 * @exception 
	 * @since  1.0.0
	 */
	public List<HashMap<String, Object>> findRolesByUserId(@Param("userId") Integer userId);
	
	
	
}


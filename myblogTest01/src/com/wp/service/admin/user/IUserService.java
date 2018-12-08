package com.wp.service.admin.user;

import java.util.List;

import com.wp.bean.User;
import com.wp.bean.WpParams;

/**
 * 
 * 
 * 包名:com.wp.service.admin.user 
 * 类名:IUserService  
 * 创建人:wenpeng
 * Email:1091654568@qq.com
 * 时间：2017年04月02日-下午10:14:53
 */
public interface IUserService {
	/**
	 * 
	 * 获取类容
	 * com.wp.service.admin.user 
	 * 方法名：findUsers
	 * 创建人：wenpeng
	 * 时间：2017年04月02日-下午10:14:53 
	 * @param params
	 * @return List<Content>
	 * @exception 
	 * @since  1.0.0
	 */
	public List<User> findUsers(WpParams params);
	/**
	 * 
	 * 获取内容总数
	 * com.wp.service.admin.user 
	 * 方法名：findUsers
	 * 创建人：wenpeng
	 * 时间：2017年04月02日-下午10:14:53 
	 * @param params
	 * @return int
	 * @exception 
	 * @since  1.0.0
	 */
	public int count(WpParams params);

	/**
	 * 修改 用户模块
	 * com.wp.service.admin.user 
	 * 方法名：findUsers
	 * 创建人：wenpeng
	 * 时间：2017年04月02日-下午10:14:53 
	 * @param id
	 * @return int
	 * @exception 
	 * @since  1.0.0
	 */
	public int updateUser(User user);
	/**
	 * 
	 * 获取登陆用户
	 * com.wp.dao.admin.user 
	 * 方法名：getLoginUser
	 * 创建人：wenpeng
	 * 时间：2017年4月2日-下午10:50:38 
	 * @param params
	 * @return User
	 * @exception 
	 * @since  1.0.0
	 */
	public User getLoginUser(WpParams params);
	/**
	 * 保存 用户模块
	 * com.wp.service.admin.user 
	 * 方法名：findUsers
	 * 创建人：wenpeng
	 * 时间：2017年04月02日-下午10:14:53 
	 * @param user
	 * @return int
	 * @exception 
	 * @since  1.0.0
	 */
	public int saveUser(User user);
	
	/**
	 * 删除 用户模块
	 * com.wp.service.admin.user 
	 * 方法名：findUsers
	 * 创建人：wenpeng
	 * 时间：2017年04月02日-下午10:14:53 
	 * @param params
	 * @return int
	 * @exception 
	 * @since  1.0.0
	 */
	public int delUser(WpParams params);
	
	/**
	 * 查询 用户模块
	 * com.wp.service.admin.user 
	 * 方法名：findUsers
	 * 创建人：wenpeng
	 * 时间：2017年04月02日-下午10:14:53 
	 * @param id
	 * @return int
	 * @exception 
	 * @since  1.0.0
	 */
	public User getUser(Integer id);
	
	/**
	 * 查询用户名是否存在
	 * @param name
	 * @return
	 */
	public int nameExist(String name);
	/**
	 * 查询邮箱是否存在
	 * @param email
	 * @return
	 */
	public int emailExist(String email);
	
}


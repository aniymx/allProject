package com.wp.dao.admin.content;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.wp.bean.Content;
import com.wp.bean.WpParams;

/**
 * 
 * 工程名:blog
 * 包名:com.wp.dao.admin
 * 类名:IAdminContentDao	后台内容操作
 * 创建人:wenpeng
 * Email:1091654568@qq.com
 * 时间：2017年4月1日-下午9:09:25 
 */
public interface IAdminContentDao {
	/**
	 * 
	 * 查询内容
	 * com.wp.dao 
	 * 方法名：findContents
	 * 创建人：wenpeng
	 * 时间：2017年3月31日-下午3:02:23 
	 * @param params void
	 * @exception 
	 * @since  1.0.0
	 */
	public List<Content> findContents(WpParams params);
	/**
	 * 
	 * 查询总数
	 * com.wp.service 
	 * 方法名：count
	 * 创建人：wenpeng
	 * 时间：2017年3月31日-下午4:04:42 
	 * @param params
	 * @return int
	 * @exception 
	 * @since  1.0.0
	 */
	public int count(WpParams params);
	/**
	 * 
	 * 修改状态的方法
	 * com.wp.dao.admin.content 
	 * 方法名：update
	 * 创建人：wenpeng
	 * 时间：2017年4月2日-上午9:26:03 
	 * @param content
	 * @return int
	 * @exception 
	 * @since  1.0.0
	 */
	public int update(Content content);
	/**
	 * 修改内容的方法
	 * @param content
	 * @return
	 */
	public int updateCon(Content content);
	/**
	 * 根据ID获取内容
	 * @param id
	 * @return
	 */
	public Content getContent(@Param("id") Integer id);
	/**
	 * 添加内容
	 * @param content
	 * @return
	 */
	public boolean add(Content content);
}

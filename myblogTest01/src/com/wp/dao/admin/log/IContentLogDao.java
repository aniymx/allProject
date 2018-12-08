package com.wp.dao.admin.log;

import java.util.HashMap;
import java.util.List;

import com.sun.javafx.collections.MappingChange.Map;
import com.wp.bean.WpParams;

/**
 * 
 * 
 * 包名:com.wp.dao.admin.log 
 * 类名:ILogDao  
 * 创建人:wenpeng
 * Email:1091654568@qq.com
 * 时间：2017年04月05日-下午06:06:43
 */
public interface IContentLogDao {
	
	/**
	 * 
	 * 获取统计信息
	 * com.wp.dao.admin.log 
	 * 方法名：groupContent
	 * 创建人：wenpeng
	 * 时间：2017年4月6日-上午10:39:15 
	 * @param params
	 * @return List<HashMap<String,Object>>
	 * @exception 
	 * @since  1.0.0
	 */
	public List<HashMap<String, Object>> groupContent(WpParams params);
	
	/**
	 * 
	 * 根据月份获取详细统计信息
	 * com.wp.dao.admin.log 
	 * 方法名：getContentsByMonth
	 * 创建人：wenpeng
	 * 时间：2017年4月6日-上午10:39:47 
	 * @param params
	 * @return List<Map<String,Object>>
	 * @exception 
	 * @since  1.0.0
	 */
	public List<Map<String, Object>> getContentsByMonth(WpParams params);
	/**
	 * 
	 * 查询该月的总数
	 * com.wp.dao.admin.log 
	 * 方法名：contentCount
	 * 创建人：wenpeng
	 * 时间：2017年4月6日-上午10:40:18 
	 * @param params
	 * @return int
	 * @exception 
	 * @since  1.0.0
	 */
	public int contentCount(WpParams params);
	
	
}


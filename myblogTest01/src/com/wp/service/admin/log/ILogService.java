package com.wp.service.admin.log;

import java.util.HashMap;
import java.util.List;

import com.sun.javafx.collections.MappingChange.Map;
import com.wp.bean.Log;
import com.wp.bean.WpParams;

/**
 * 
 * 
 * 包名:com.wp.service.admin.log 
 * 类名:ILogService  
 * 创建人:wenpeng
 * Email:1091654568@qq.com
 * 时间：2017年04月05日-下午06:06:43
 */
public interface ILogService {
	/**
	 * 
	 * <!-- 查询日志信息 -->
	 * com.wp.dao.admin.log 
	 * 方法名：findLogs
	 * 创建人：wenpeng
	 * 时间：2017年4月5日-下午6:18:22 
	 * @param params
	 * @return List<Log>
	 * @exception 
	 * @since  1.0.0
	 */
	public List<Log> findLogs(WpParams params);
	/**
	 * 
	 * 获取总数
	 * com.wp.dao.admin.log 
	 * 方法名：count
	 * 创建人：wenpeng
	 * 时间：2017年4月5日-下午6:19:28 
	 * @return int
	 * @exception 
	 * @since  1.0.0
	 */
	public int count(WpParams params);
	/**
	 * 
	 * 保存日志信息
	 * com.wp.dao.admin.log 
	 * 方法名：saveLog
	 * 创建人：wenpeng
	 * 时间：2017年4月5日-下午6:20:40 
	 * @param log
	 * @return int
	 * @exception 
	 * @since  1.0.0
	 */
	public int saveLog(Log log);
	/**
	 * 
	 * 获取日志的统计信息
	 * com.wp.dao.admin.log 
	 * 方法名：groupLogs
	 * 创建人：wenpeng
	 * 时间：2017年4月6日-下午3:26:31 
	 * @param params
	 * @return List<HashMap<String,Object>>
	 * @exception 
	 * @since  1.0.0
	 */
	public List<HashMap<String, Object>> groupLogs(WpParams params);
	
	/**
	 * 
	 * 获取该月的详细日志
	 * com.wp.dao.admin.log 
	 * 方法名：getLogsByMonth
	 * 创建人：wenpeng
	 * 时间：2017年4月6日-下午3:25:38 
	 * @param params
	 * @return List<Map<String,Object>>
	 * @exception 
	 * @since  1.0.0
	 */
	public List<Map<String, Object>> getLogsByMonth(WpParams params);
	/**
	 * 
	 * 该月总数
	 * com.wp.dao.admin.log 
	 * 方法名：logCount
	 * 创建人：wenpeng
	 * 时间：2017年4月6日-下午3:25:10 
	 * @param params
	 * @return int
	 * @exception 
	 * @since  1.0.0
	 */
	public int logCount(WpParams params);
}


package com.wp.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.wp.bean.Content;
import com.wp.bean.Top;
import com.wp.bean.WpParams;

/**
 * 
 * 工程名:blog
 * 包名:com.wp.dao
 * 类名:IContentDao	内容操作
 * 创建人:wenpeng
 * Email:1091654568@qq.com
 * 时间：2017年3月31日-下午2:58:53 
 */
public interface IContentDao {
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
	 * 修改内容
	 * com.wp.dao 
	 * 方法名：update
	 * 创建人：wenpeng
	 * 时间：2017年3月31日-下午8:28:29 
	 * @param content
	 * @return int
	 * @exception 
	 * @since  1.0.0
	 */
	public int update(Content content);
	/**
	 * 
	 * 根据ID查询内容
	 * com.wp.dao 
	 * 方法名：getContent
	 * 创建人：wenpeng
	 * 时间：2017年3月31日-下午8:29:12 
	 * @param id
	 * @return Content
	 * @exception 
	 * @since  1.0.0
	 */
	public Content getContent(@Param("id") Integer id, @Param("channelId") Integer channelId);
	
	/**
	 * 获取头部的内容
	 * @return
	 */
	public Top getTop();
	/**
	 * 修改头部背景和文字
	 * @param top
	 * @return
	 */
	public int updateTop(Top top);
}

package com.wp.dao;

import java.util.List;

import com.wp.bean.Link;

public interface ILinkDao {
	/**
	 * 获取友链
	 * @return
	 */
	public List<Link> getLinks();
	
	/**
	 * 添加友情链接
	 * @return
	 */
	public int saveLink(Link link);
	/**
	 * 删除友情链接
	 * @return
	 */
	public int delLink(Integer id);
	/**
	 * 修改友情链接
	 * @return
	 */
	public int updateLink(Link link);
}

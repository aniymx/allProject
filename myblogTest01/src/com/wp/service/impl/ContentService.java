package com.wp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wp.bean.Content;
import com.wp.bean.Top;
import com.wp.bean.WpParams;
import com.wp.dao.IContentDao;
import com.wp.service.IContentService;

/**
 * 
 * 工程名:blog
 * 包名:com.wp.service.impl
 * 类名:ContentService
 * 创建人:wenpeng
 * Email:1091654568@qq.com
 * 时间：2017年3月31日-下午3:14:06 
 */
@Service
public class ContentService implements IContentService{
	@Autowired
	private IContentDao contentDao;
	@Override
	public List<Content> findContents(WpParams params) {
		return contentDao.findContents(params);
	}
	@Override
	public int count(WpParams params) {
		return contentDao.count(params);
	}
	@Override
	@Transactional
	public int update(Content content) {
		return contentDao.update(content);
	}
	@Override
	public Content getContent(Integer id,Integer channleId) {
		return contentDao.getContent(id,channleId);
	}
	@Override
	public Top getTop() {
		return contentDao.getTop();
	}
	@Override
	public int updateTop(Top top) {
		return contentDao.updateTop(top);
	}

}

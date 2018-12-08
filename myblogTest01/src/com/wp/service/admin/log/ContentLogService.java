package com.wp.service.admin.log;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sun.javafx.collections.MappingChange.Map;
import com.wp.bean.WpParams;
import com.wp.dao.admin.log.IContentLogDao;

/**
 * 
 * 工程名:blog
 * 包名:com.wp.service.admin.log
 * 类名:ContentLogService
 * 创建人:wenpeng
 * Email:1091654568@qq.com
 * 时间：2017年4月6日-上午10:41:49 
 */
@Service
public class ContentLogService implements IContentLogService {
	@Autowired
	private IContentLogDao contentLogDao;
	@Override
	public List<HashMap<String, Object>> groupContent(WpParams params) {
		return contentLogDao.groupContent(params);
	}

	@Override
	public List<Map<String, Object>> getContentsByMonth(WpParams params) {
		return contentLogDao.getContentsByMonth(params);
	}

	@Override
	public int contentCount(WpParams params) {
		return contentLogDao.contentCount(params);
	}

}

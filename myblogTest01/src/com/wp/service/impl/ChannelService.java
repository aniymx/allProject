package com.wp.service.impl;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wp.bean.Channel;
import com.wp.dao.IChannelDao;
import com.wp.service.IChannelService;

/**
 * 
 * 包名:com.wp.service.impl
 * 类名:ChannelService
 * 创建人:wenpeng
 * Email:1091654568@qq.com
 * 时间：2017年4月1日-下午3:00:08
 */
@Service
public class ChannelService implements IChannelService{
	@Autowired
	private IChannelDao channelDao;
	@Override
	public Channel getChannelByName(String name) {
		return channelDao.getChannelByName(name);
	}

}

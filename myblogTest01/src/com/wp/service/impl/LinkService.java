package com.wp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wp.bean.Link;
import com.wp.dao.ILinkDao;
import com.wp.service.ILinkService;

@Service
public class LinkService implements ILinkService{

	@Autowired
	private ILinkDao linkDao;

	@Override
	public List<Link> getLinks() {
		return linkDao.getLinks();
	}

	@Override
	public int saveLink(Link link) {
		return linkDao.saveLink(link);
	}

	@Override
	public int delLink(Integer id) {
		return linkDao.delLink(id);
	}

	@Override
	public int updateLink(Link link) {
		return linkDao.updateLink(link);
	}
	
	
	
	
}

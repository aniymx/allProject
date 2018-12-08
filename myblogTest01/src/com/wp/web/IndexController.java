package com.wp.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.wp.bean.Content;
import com.wp.bean.Link;
import com.wp.bean.Top;
import com.wp.bean.WpParams;
import com.wp.service.IContentService;
import com.wp.service.ILinkService;

/**
 * 
 * 工程名:blog
 * 包名:com.wp.web
 * 类名:IndexController	首页
 * 创建人:wenpeng
 * Email:1091654568@qq.com
 * 时间：2017年3月31日-上午10:38:33 
 */
@Controller
public class IndexController {
	@Autowired
	private IContentService contentService;
	@Autowired
	private ILinkService linkService;
	//主页
	@RequestMapping("index")
	public String index(HttpServletRequest request){
		
		WpParams params = new WpParams();
		params.setOrder("c.create_time desc");
		params.setPush(1);
		//查询推荐文章
		List<Content> pushc = contentService.findContents(params);
		request.setAttribute("push", pushc);
		WpParams cp = new WpParams();
		//设置根据创建时间排序
		cp.setOrder("c.create_time desc");
		//查询最新文章
		List<Content> newContent = contentService.findContents(cp);
		request.setAttribute("newContent", newContent);
		return "index";
	}
	/*主页分页模板*/
	@RequestMapping("template")
	public ModelAndView template(WpParams params){
		ModelAndView modelAndView = new ModelAndView();
		params.setOrder("c.is_top desc,c.create_time desc");
		List<Content> contents = contentService.findContents(params);
		int itemCount = contentService.count(params);
		modelAndView.addObject("contents",contents);
		modelAndView.addObject("itemCount",itemCount);
		modelAndView.setViewName("template");
		return modelAndView;
	}
	
	@RequestMapping("bottom")
	public ModelAndView bottom(){
		ModelAndView mView = new ModelAndView();
		List<Link> links = linkService.getLinks();
		mView.setViewName("common/bottom");
		mView.addObject("links", links);
		return mView;
	}
	
	@RequestMapping("header")
	public ModelAndView header(){
		ModelAndView mView = new ModelAndView();
		Top top = contentService.getTop();
		mView.setViewName("common/header");
		mView.addObject("top", top);
		return mView;
	}
	
}

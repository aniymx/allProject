package com.wp.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.wp.bean.Channel;
import com.wp.bean.Content;
import com.wp.bean.WpParams;
import com.wp.service.IChannelService;
import com.wp.service.IContentService;

/**
 * 
 * 工程名:blog
 * 包名:com.wp.web
 * 类名:ChannelPageController	 栏目
 * 创建人:wenpeng
 * Email:1091654568@qq.com
 * 时间：2017年4月1日-下午1:52:15
 */
@Controller
@RequestMapping("index")
public class ChannelPageController {
	@Autowired
	private IContentService contentService;
	@Autowired
	private IChannelService channelService;
	
	//会在每个目标方法执行之前被Spring MVC 调用 
	/*@ModelAttribute
	public void setAttr(){
		
	}*/
	//栏目
	@RequestMapping("/{channelName}")
	public ModelAndView web(@PathVariable("channelName")String channelName){
		Channel channel = channelService.getChannelByName(channelName);
		ModelAndView modelAndView = new ModelAndView();
		if (channel != null) {
			WpParams params = new WpParams();
			params.setChannelId(channel.getId());
			params.setPageSize(5);
			List<Content> contents = contentService.findContents(params);
			modelAndView.addObject("contents",contents);
			modelAndView.setViewName("list");
			WpParams pp = new WpParams();
			pp.setOrder("c.create_time desc");
			pp.setPush(1);
			pp.setChannelId(channel.getId());
			//查询推荐文章
			List<Content> pushc = contentService.findContents(params);
			modelAndView.addObject("push", pushc);
			WpParams cp = new WpParams();
			//设置根据创建时间排序
			cp.setOrder("c.create_time desc");
			cp.setChannelId(channel.getId());
			//查询最新文章
			List<Content> newContent = contentService.findContents(cp);
			modelAndView.addObject("newContent", newContent);
			return modelAndView;
		}else {
			return modelAndView;
		}
	}
	//滚动加载
	@ResponseBody
	@RequestMapping(value = "/load",method=RequestMethod.POST)
	public List<Content> load(WpParams params){
		List<Content> contents = contentService.findContents(params);
		return contents;
	}
}

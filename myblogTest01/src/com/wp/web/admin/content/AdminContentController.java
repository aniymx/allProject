package com.wp.web.admin.content;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.wp.bean.Content;
import com.wp.bean.WpParams;
import com.wp.service.admin.content.IAdminContentService;

/**
 * 
 * 工程名:blog
 * 包名:com.wp.web.admin.content
 * 类名:AdminContentController
 * 创建人:wenpeng
 * Email:1091654568@qq.com
 * 时间：2017年4月1日-下午10:38:27 
 */
@RequestMapping("sysmgr/admin/content")
@Controller
public class AdminContentController {
	@Autowired
	private IAdminContentService adminContentService;
	
	/*内容操作页面*/
	@RequestMapping("list")
	public String list(WpParams params){
		return "admin/content/list";
	}
	/*内容添加页面*/
	@RequestMapping("add")
	public String add(WpParams params,HttpServletRequest request){
		if(params.getId() != null){
			Content content = adminContentService.getContent(params.getId());
			request.setAttribute("contentById",content);
		}
		return "admin/content/add";
	}
	
	@RequestMapping("template")
	public ModelAndView template(WpParams params){
		ModelAndView modelAndView = new ModelAndView();
		params.setOrder("c.create_time desc");
		List<Content> contents = adminContentService.findContents(params);
		int count = adminContentService.count(params);
		modelAndView.addObject("contents",contents);
		modelAndView.addObject("itemCount",count);
		modelAndView.setViewName("admin/content/template");
		return modelAndView;
	}
	/*修改内容状态*/
	@ResponseBody
	@RequestMapping(value="update",method=RequestMethod.POST)
	public String update(Content content){
		adminContentService.update(content);
		return "success";
	}
	
	/*保存内容*/
	@ResponseBody
	@RequestMapping(value="saveCon",method=RequestMethod.POST)
	public String saveContent(Content content){
		if(content != null){
			boolean flag = adminContentService.add(content);
			if (flag) {
				return "success";
			}else{
				return "fail";
			}
		}else{
			return "error";
		}
	}
	/*修改内容*/
	@ResponseBody
	@RequestMapping(value="updateCon",method=RequestMethod.POST)
	public String updateContent(Content content){
		if(content != null){
			content.setUpdateTime(new Date());
			try {
				adminContentService.updateCon(content);
				return "success";
			} catch (Exception e) {
				e.printStackTrace();
				return "fail";
			}
		}else{
			return "error";
		}
	}
	
}

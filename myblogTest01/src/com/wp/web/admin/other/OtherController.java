package com.wp.web.admin.other;

import java.io.PrintWriter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.wp.bean.Link;
import com.wp.bean.Top;
import com.wp.service.IContentService;
import com.wp.service.ILinkService;

/**
 * 其他操作 
 * @author wp
 *
 */
@Controller
@RequestMapping("sysmgr/admin/other")
public class OtherController {
	@Autowired
	private IContentService contentService;
	@Autowired
	private ILinkService linkService;
	/*返回头部管理页面和查询出来的内容*/
	@RequestMapping("header")
	public ModelAndView header(){
		ModelAndView modelAndView = new ModelAndView();
		Top top = contentService.getTop();
		modelAndView.addObject("top",top);
		modelAndView.setViewName("admin/other/header");
		return modelAndView;
	}
	/*返回友情链接管理页面*/
	@RequestMapping("link")
	public ModelAndView link(){
		List<Link> links = linkService.getLinks();
		ModelAndView mv = new ModelAndView();
		mv.addObject("links", links);
		mv.setViewName("admin/other/link");
		return mv;
	}
	/*保存头部信息*/
	@ResponseBody
	@RequestMapping(value="saveTop",method=RequestMethod.POST)
	public String saveTop(Top top){
		if(top != null){
			try {
				contentService.updateTop(top);
				return "success";
			} catch (Exception e) {
				e.printStackTrace();
				return "fail";
			}
		}
		return "error";
	}
	/*保存友情链接*/
	@ResponseBody
	@RequestMapping(value="saveLink",method=RequestMethod.POST)
	public int saveTop(Link link){
		if(link != null){
			try {
				linkService.saveLink(link);
				return link.getId();
			} catch (Exception e) {
				e.printStackTrace();
				return -1;
			}
		}
		return -1;
	}
	/*删除友情链接*/
	@ResponseBody
	@RequestMapping(value="delLink",method=RequestMethod.POST)
	public String delLink(Integer id){
		if(id != null){
			try {
				linkService.delLink(id);
				return "success";
			} catch (Exception e) {
				e.printStackTrace();
				return "fail";
			}
		}
		return "error";
	}
	/*修改友情链接*/
	@ResponseBody
	@RequestMapping(value="updateLink",method=RequestMethod.POST)
	public String updateLink(Link link){
		if(link != null){
			try {
				linkService.updateLink(link);
				return "success";
			} catch (Exception e) {
				e.printStackTrace();
				return "fail";
			}
		}
		return "error";
	}
}

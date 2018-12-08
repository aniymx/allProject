package com.wp.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.wp.bean.Content;
import com.wp.bean.WpParams;
import com.wp.service.IContentService;
/**
 * 前台内容操作 
 * @author wp
 *
 */
@Controller
@RequestMapping("content")
public class ContentController {
	@Autowired
	private IContentService contentService;
	/**
	 * 根据ID返回详细页面
	 * @param id  内容ID
	 * @param cid  内容的栏目ID
	 * @return
	 */
	@RequestMapping("html/{id}/{cid}")
	public ModelAndView queryContent(@PathVariable("id")Integer id,@PathVariable("cid")Integer cid){
		ModelAndView mv = new ModelAndView();
		Content content = contentService.getContent(id,cid);
		WpParams params = new WpParams();
		params.setPush(1);
		params.setIsTop(1);
		params.setOrder("c.create_time desc");
		params.setPageSize(8);
		params.setChannelId(content.getChannelId());
		List<Content> contents = contentService.findContents(params);
		mv.setViewName("admin/template/template");
		mv.addObject("content",content);
		mv.addObject("guessCon", contents);
		
		return mv;
		
	}
	
	/*保存点击数*/
	@ResponseBody
	@RequestMapping(value="saveHits",method=RequestMethod.POST)
	public void saveHits(Content content){
		if(content.getId() != null){
			try {
				Content con = contentService.getContent(content.getId(),null);
				con.setHits(con.getHits()+1);
				contentService.update(con);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	/*查询点击数*/
	@ResponseBody
	@RequestMapping(value="queryHits",method=RequestMethod.POST)
	public int queryHits(Integer id){
		if(id != null){
			try {
				Content con = contentService.getContent(id,null);
				return con.getHits();
			} catch (Exception e) {
				e.printStackTrace();
				return -1;
			}
		}else{
			return -1;
		}
	}
}

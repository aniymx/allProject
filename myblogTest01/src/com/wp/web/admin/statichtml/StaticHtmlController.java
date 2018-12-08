package com.wp.web.admin.statichtml;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wp.bean.Content;
import com.wp.service.IContentService;
import com.wp.util.Utils;

/**
 * 
 * 
 * 工程名:wp_admin
 * 包名:com.wp.web.statichtml
 * 类名:StaticHtmlController  页面静态化操作类
 * 创建人:wenpeng
 * Email:1091654568@qq.com
 * 时间：2017年3月29日-下午2:49:25
 */
@Controller
@RequestMapping("sysmgr/admin/static")
public class StaticHtmlController {
	
	@Autowired
	private IContentService contentService;
	
	@ResponseBody
	@RequestMapping(value="/html/{id}",method=RequestMethod.POST)
	public String staticHtml(@PathVariable("id")Integer id,HttpServletRequest request,HttpServletResponse response){
		Integer cid = Integer.parseInt(request.getParameter("cid"));
		Content content = contentService.getContent(id,cid);
		if(content!=null){
			//模板1
			String temppath = "/WEB-INF/pages/admin/template/template_min.jsp";
			
			String name = StaticHtmlUtils.staticContent(id,cid,content.getStaticLink(),temppath,request,response);
			if(Utils.isNotEmpty(name)){
				//静态化成功,更改路径
				content.setStaticLink(name);
				//content.setUpdateTime(new Date());
				contentService.update(content);
				return "success";
			}else{
				return "fail";
			}
		}else{
			return "fail";
		}
	}
	
	
}

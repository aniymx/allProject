package com.wp.tag;

import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.wp.bean.Content;
import com.wp.bean.WpParams;
import com.wp.service.IContentService;
import com.wp.util.Utils;

/**
 * 
 * 工程名:wp_admin
 * 包名:com.wp.tag
 * 类名:ContentBeanTag
 * 创建人:wenpeng
 * Email:1091654568@qq.com
 * 时间：2017年3月29日-下午4:56:25 
 */
public class ContentBeanTag extends BodyTagSupport{
	
	private String var;
	private Integer cid;
	private Integer channelId;
	@Override
	public int doStartTag() throws JspException {
		ServletContext context = this.pageContext.getServletContext();
		WebApplicationContext ctx = WebApplicationContextUtils.getRequiredWebApplicationContext(context);
		IContentService contentService = (IContentService) ctx.getBean("contentService");
		Content content = contentService.getContent(cid,channelId);
		WpParams params = new WpParams();
		params.setPush(1);
		params.setIsTop(1);
		params.setOrder("c.create_time desc");
		params.setPageSize(8);
		params.setChannelId(content.getChannelId());
		List<Content> contents = contentService.findContents(params);
		StringBuffer buffer = new StringBuffer();
		for (Content con : contents) {
			buffer.append("<li><a href="+con.getStaticLink()+">"+con.getTitle()+"</a></li>");
		}
		if (Utils.isEmpty(var))var = "content";
		pageContext.setAttribute(var, content);
		pageContext.setAttribute("guessCon", buffer.toString());
		return SKIP_BODY;
	}
	@Override
	public int doEndTag() throws JspException {
		return EVAL_PAGE;
	}
	public void setVar(String var) {
		this.var = var;
	}
	public void setCid(Integer cid) {
		this.cid = cid;
	}
	public void setChannelId(Integer channelId) {
		this.channelId = channelId;
	}
	
}

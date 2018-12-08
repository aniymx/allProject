package com.wp.tag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;

import com.wp.util.Utils;

/**
 * 
 * 工程名:wp_admin
 * 包名:com.wp.tag
 * 类名:LinkTag	自定义a标签
 * 创建人:wenpeng
 * Email:1091654568@qq.com
 */
public class LinkTag extends BodyTagSupport{
	//链接
	private String href = "javascript:void(0)";
	//类名
	private String className;
	//ID
	private String id;
	//扩展类容
	private String html;
	//文本内容
	private String text;

	@Override
	public int doStartTag() throws JspException {
		StringBuilder builder = new StringBuilder();
		builder.append("<a href="+href+"");
		if (Utils.isNotEmpty(className)) {
			builder.append(" class="+className+"");
		}
		if (Utils.isNotEmpty(id)) {
			builder.append(" id="+id+"");
		}
		if (Utils.isNotEmpty(html)) {
			builder.append(" "+html+"");
		}
		builder.append(">"+text);
		builder.append("</a>");
		System.out.println(builder.toString());
		try {
			this.pageContext.getOut().print(builder.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return SKIP_BODY;
	}
	
	
	public void setHref(String href) {
		this.href = href;
	}
	
	public void setClassName(String className) {
		this.className = className;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public void setHtml(String html) {
		this.html = html;
	}


	public void setText(String text) {
		this.text = text;
	}
	
	
}

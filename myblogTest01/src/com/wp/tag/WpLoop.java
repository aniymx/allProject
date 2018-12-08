package com.wp.tag;

import java.util.Collection;
import java.util.Iterator;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

public class WpLoop extends TagSupport{
	
	private Object items;
	private String var;
	private Iterator iterator;
	/*
	 * 自定义标签的第一步：
	 * 1:继承TagSupport 或者BodyTagSupport
	 * 2:覆盖里面对应标签的方法 快捷键：Alt+Shift+S
	 * 3:注册：tld文件中注册标签
	 * 4:引入：<%@taglib uri="/web-inf/tld/tz.tld" prefix="tz" %>
	 * 5:调用
	 * <div></div>
	 * <br/>
	 * 
	 * 
	 * 后台标签类注意点：
	 * 1:定义标签属性，只需要生成set方法即可,必须和tld文件中的属性名保持一致
	 * 2:
	 * doStartTag:
	 * SKIP_BODY:忽略标签体的主题内容，这默认值
	 * EVAL_BODY_INCLUDE:要求JSP容器记性执行标签体内容并将结果返回给页面
	 * */
	
	public void setItems(Object items) {
		this.items = items;
	}
	public void setVar(String var) {
		this.var = var;
	}
	@Override
	//标签开始 <wp:loop>
	public int doStartTag() throws JspException {
		if(items == null || !(items instanceof Collection)){
			return SKIP_BODY;
		}
		iterator = ((Collection)items).iterator();
		if(iterator.hasNext()) {
			this.pageContext.setAttribute(var,iterator.next());
			return EVAL_BODY_INCLUDE;
		}else {
			return SKIP_BODY;
		}
	
	}
	//标签体
	@Override
	public int doAfterBody() throws JspException {
		if(iterator.hasNext()) {
			this.pageContext.setAttribute(var,iterator.next());
			return EVAL_BODY_AGAIN;
		}else {
			return SKIP_BODY;
		}
	}
	//标签结束</wp:loop>
	@Override
	public int doEndTag() throws JspException {
		return EVAL_PAGE;
	}
	
	@Override
	public void release() {
		items = null;
		super.release();
	}
	
}

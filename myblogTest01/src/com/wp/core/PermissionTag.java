package com.wp.core;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyTagSupport;

import com.wp.dto.WpConstant;
import com.wp.util.Utils;

/**
 * 
 * 工程名:blog
 * 包名:com.wp.core
 * 类名:PermissionTag
 * 创建人:wenpeng
 * Email:1091654568@qq.com
 * 时间：2017年4月5日-下午4:32:26 
 */
public class PermissionTag extends BodyTagSupport{
	
	private String url;
	private String model;
	private String method;
	private List<HashMap<String, Object>> datas;
	@Override
	public void doInitBody() throws JspException {
		datas = (List<HashMap<String, Object>>) this.pageContext.getSession().getAttribute(WpConstant.SESSION_USER_PERMISSION);
		super.doInitBody();
	}
	@Override
	public int doAfterBody() throws JspException {
		try {
			String content = this.getBodyContent().getString();
			JspWriter out = this.getPreviousOut();
			if (getPermission()) {
				out.print(content);
			}else {
				out.print("");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return SKIP_BODY;
	}
	private boolean getPermission(){
		boolean flag = false;
		if (datas != null && datas.size() > 0) {
			for (HashMap<String, Object> hashMap : datas) {
				//获取访问的权限路径   = model+method
				//String pUrl = String.valueOf(hashMap.get("url"));
				//获取访问权限的模块
				String pModel = String.valueOf(hashMap.get("model"));
				//获取访问权限的方法
				String pMethod = String.valueOf(hashMap.get("method"));
				if (Utils.isNotEmpty(pMethod) && 
					Utils.isNotEmpty(pMethod) && 
					pModel.equalsIgnoreCase(model) &&
					pMethod.equalsIgnoreCase(method)) {
					flag = true;
					break;
				}
			}
		}
		return flag;
	}
	
	public void setUrl(String url) {
		this.url = url;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public void setMethod(String method) {
		this.method = method;
	}
	
}

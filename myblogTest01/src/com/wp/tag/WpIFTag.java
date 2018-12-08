package com.wp.tag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;

public class WpIFTag extends BodyTagSupport{
	
	private Boolean test;
	public Boolean getTest() {
		return test;
	}

	public void setTest(Boolean test) {
		this.test = test;
	}
	@Override
	public int doStartTag() throws JspException {
		
		return EVAL_BODY_INCLUDE;
		
	}
	
	
}

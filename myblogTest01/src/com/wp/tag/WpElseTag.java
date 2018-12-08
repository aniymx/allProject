package com.wp.tag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;

public class WpElseTag extends BodyTagSupport{

	
	private static final long serialVersionUID = 1L;

	@Override
	public int doStartTag() throws JspException {
		
		WpIFTag ifTag = (WpIFTag) this.getParent();
		if(ifTag != null && !ifTag.getTest()) {
			return EVAL_BODY_INCLUDE;
		}else {
			return SKIP_BODY;
		}
	}
}

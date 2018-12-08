package com.wp.tag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

public class PrintNum extends TagSupport {

	// 次数
	private Integer num;
	// 索引
	private int indexNum = 0;
	private String index;

	public void setIndex(String index) {
		this.index = index;
	}

	@Override
	public int doStartTag() throws JspException {
		if (num <= 0) {
			return SKIP_BODY;
		}
		this.pageContext.setAttribute(index, indexNum);
		return EVAL_BODY_INCLUDE;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	@Override
	public int doAfterBody() throws JspException {
		if (num > 1) {
			num--;
			indexNum++;
			this.pageContext.setAttribute(index, indexNum);
			return EVAL_BODY_AGAIN;
		}
		return SKIP_BODY;

	}

	@Override
	public int doEndTag() throws JspException {
		indexNum = 0;
		index = null;
		return EVAL_PAGE;
	}

	@Override
	public void release() {
		indexNum = 0;
		index = null;
		super.release();
	}

}

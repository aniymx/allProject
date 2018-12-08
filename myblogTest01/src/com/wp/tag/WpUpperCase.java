package com.wp.tag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyTagSupport;

public class WpUpperCase extends BodyTagSupport{
	/**
	 * BodyTagSupport与TagSupport的区别
	 * TagSupport无法获取标签体的内容
	 * 		1：是将数据放在作用域中进行返回
	 * BodyTagSupport可以获取标签体中的内容
	 * 		1：是将数据放在作用域中进行返回
	 * 		2：通过输出流this.getPreviousOut()
	 * 		3:获取标签体内容this.getBodyContent()
	 */
	
	//次数
	private int count = 1;

	public void setCount(int count) {
		this.count = count;
	}

	@Override
	public int doAfterBody() throws JspException {
		if(count > 0) {
			try {
				String content = this.getBodyContent().getString();
				JspWriter writer = this.getPreviousOut();
				writer.print(content.toUpperCase());
				count--;
				//清除缓存的标签的主题内容
				bodyContent.clearBody();
			} catch (Exception e) {
				e.printStackTrace();
			}
			return EVAL_BODY_AGAIN;
		}else {
			return SKIP_BODY;
		}
	}


	@Override
	public void release() {
		super.release();
	}
	
}

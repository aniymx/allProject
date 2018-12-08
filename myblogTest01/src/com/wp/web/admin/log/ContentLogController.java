package com.wp.web.admin.log;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.json.JSONException;
import org.apache.struts2.json.JSONUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sun.javafx.collections.MappingChange.Map;
import com.wp.bean.WpParams;
import com.wp.service.admin.log.IContentLogService;

/**
 * 
 * 工程名:blog
 * 包名:com.wp.web.admin.log
 * 类名:ContentLogController
 * 创建人:wenpeng
 * Email:1091654568@qq.com
 * 时间：2017年4月6日-上午10:43:11 
 */
@RequestMapping("sysmgr/admin/log")
@Controller
public class ContentLogController {
	@Autowired
	private IContentLogService contentLogService;
	@ResponseBody
	@RequestMapping(value="conlist",method=RequestMethod.POST)
	public List<HashMap<String, Object>> list(WpParams params){
		List<HashMap<String, Object>> adminlogs = contentLogService.groupContent(params);
		return adminlogs;
	}
	
	@ResponseBody
	@RequestMapping(value="conMonthList",method=RequestMethod.POST)
	public String monthList(WpParams params,HttpServletRequest request) throws JSONException{
		params.setOrder("create_time desc");
		List<Map<String, Object>> contents = contentLogService.getContentsByMonth(params);
		int count = contentLogService.contentCount(params);
		request.setAttribute("count",count);
		return JSONUtil.serialize(contents)+count;
	}
}
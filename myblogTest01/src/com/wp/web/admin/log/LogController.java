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
import org.springframework.web.servlet.ModelAndView;

import com.sun.javafx.collections.MappingChange.Map;
import com.wp.bean.Log;
import com.wp.bean.WpParams;
import com.wp.service.admin.log.ILogService;

/**
 * 
 * 包名:com.wp.web.log
 * 类名:LogController  日志模块
 * 创建人:wenpeng 
 * Email:1091654568@qq.com
 * 时间：2017年04月05日-下午06:06:43
 */
@RequestMapping("sysmgr/admin/log")
@Controller
public class LogController {
	@Autowired
	private ILogService logService;
	@RequestMapping("list")
	public String list(){
		return "admin/log/list";
	}
	@RequestMapping("viewlist")
	public String viewlist(){
		return "admin/log/viewlist";
	}
	
	@RequestMapping("template")
	public ModelAndView template(WpParams params){
		ModelAndView modelAndView = new ModelAndView();
		List<Log> logs = logService.findLogs(params);
		int count = logService.count(params);
		modelAndView.addObject("logs",logs);
		modelAndView.addObject("itemCount",count);
		modelAndView.setViewName("admin/log/template");
		return modelAndView;
	}
	@ResponseBody
	@RequestMapping(value="loglist",method=RequestMethod.POST)
	public List<HashMap<String, Object>> list(WpParams params){
		List<HashMap<String, Object>> adminlogs = logService.groupLogs(params);
		return adminlogs;
	}
	
	@ResponseBody
	@RequestMapping(value="logMonthList")
	public String monthList(WpParams params,HttpServletRequest request) throws JSONException{
		params.setOrder("create_time desc");
		List<Map<String, Object>> contents = logService.getLogsByMonth(params);
		int count = logService.logCount(params);
		request.setAttribute("count",count);
		return JSONUtil.serialize(contents)+count;
	}

}


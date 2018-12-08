package com.wp.web.admin.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.wp.bean.User;
import com.wp.bean.WpParams;
import com.wp.service.admin.user.IUserService;

/**
 * 
 * 包名:com.wp.web.user
 * 类名:UserController  用户模块
 * 创建人:wenpeng 
 * Email:1091654568@qq.com
 * 时间：2017年04月02日-下午10:14:53
 */
@RequestMapping("sysmgr/admin/user")
@Controller
public class UserController {
	@Autowired
	private IUserService userService;
	@RequestMapping("list")
	public String list(WpParams params){
		return "admin/user/list";
	}
	
	@RequestMapping("template")
	public ModelAndView template(WpParams params){
		ModelAndView modelAndView = new ModelAndView();
		List<User> users = userService.findUsers(params);
		int count = userService.count(params);
		modelAndView.addObject("users",users);
		modelAndView.addObject("itemCount",count);
		modelAndView.setViewName("admin/user/template");
		return modelAndView;
	}
	/*禁止用户操作*/
	@ResponseBody()
	@RequestMapping(value="forbid/{opid}",method=RequestMethod.POST)
	public String forbid(@PathVariable("opid")Integer opid,Integer state){
		User user = new User();
		//登陆限制 0禁止登陆1不禁止
		user.setForbiden(state);
		user.setId(opid);
		userService.updateUser(user);
		return "success";
	}
	/*删除操作*/
	@ResponseBody()
	@RequestMapping(value="del/{opid}",method=RequestMethod.POST)
	public String del(@PathVariable("opid")Integer opid,Integer state){
		User user = new User();
		//删除状态0未删除1删除
		user.setIsDelete(state);
		user.setId(opid);
		userService.updateUser(user);
		return "success";
	}
	/*修改用户*/
	@ResponseBody()
	@RequestMapping("update")
	public String update(User user){
		userService.updateUser(user);
		return "success";
	}
	/*判断用户名是否存在*/
	@RequestMapping("isName")
	@ResponseBody()
	public String isName(String name){
		try {
			userService.nameExist(name);
			return "yes";
		} catch (Exception e) {
			return "no";
		}
	}
	/*判断邮箱是否存在*/
	@RequestMapping("isEmail")
	@ResponseBody()
	public String isEmail(String email){
		try {
			userService.emailExist(email);
			return "yes";
		} catch (Exception e) {
			return "no";
		}
	}
	/*保存用户*/
	@ResponseBody
	@RequestMapping(value="saveUser",method=RequestMethod.POST)
	public int saveUser(User user){
		if (user!= null) {
			try {
				userService.saveUser(user);
				return user.getId();
			} catch (Exception e) {
				e.printStackTrace();
				return -1;
			}
		}else{
			return -1;
		}
	}
}


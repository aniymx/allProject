package com.wp.bean;
import java.util.Date;

/**
 * 
 * 
 * 日志模块
 * 类名:Log
 * 创建人:wenpeng
 * Email:1091654568@qq.com
 * 时间：2017年04月05日-下午06:06:43
 */
public class Log implements java.io.Serializable {
	
	private Integer id;/* 主键 */
	private String classname;// 类名
	private String method;// 方法
	private Long time;// 事件
	private Date createTime;//创建时间
	private String ip;// ip
	private String ipAddress;//IP地址
	private Integer userId; //用户ID
	private String username;
	private String model;
	private String description;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getClassname() {
		return classname;
	}
	public void setClassname(String classname) {
		this.classname = classname;
	}
	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	}
	public Long getTime() {
		return time;
	}
	public void setTime(Long time) {
		this.time = time;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getIpAddress() {
		return ipAddress;
	}
	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

}


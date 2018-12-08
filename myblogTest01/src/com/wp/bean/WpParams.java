package com.wp.bean;

import java.util.Date;

/**
 * 
 * 
 * 工程名:blog
 * 包名:com.wp.bean
 * 类名:WpParams  参数类
 * 创建人:wenpeng
 * Email:1091654568@qq.com
 * 时间：2017年3月31日-下午3:12:23
 */
public class WpParams {
	private Integer id;
	private String username;
	private String password;
	private String email;
	private String telephone;
	private String keyword;
	private Integer channelId;
	private Integer pageNo = 0;
	private Integer pageSize = 10;
	private Integer totalCount = 0;
	private String order;
	private Integer isDelete;
	private Integer isTop;
	private Integer status;
	private Integer push;
	private Integer isComment;
	private Integer logYear;
	private Integer logMonth;
	private Integer logDay;
	private Date createTime;
	private Integer roleId;
	private Integer mark;
	public Integer getRoleId() {
		return roleId;
	}
	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Integer getChannelId() {
		return channelId;
	}
	public void setChannelId(Integer channelId) {
		this.channelId = channelId;
	}
	public Integer getPageNo() {
		return pageNo;
	}
	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	public Integer getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}
	public String getOrder() {
		return order;
	}
	public void setOrder(String order) {
		this.order = order;
	}
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	public Integer getIsDelete() {
		return isDelete;
	}
	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}
	public Integer getIsTop() {
		return isTop;
	}
	public void setIsTop(Integer isTop) {
		this.isTop = isTop;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Integer getPush() {
		return push;
	}
	public void setPush(Integer push) {
		this.push = push;
	}
	public Integer getIsComment() {
		return isComment;
	}
	public void setIsComment(Integer isComment) {
		this.isComment = isComment;
	}
	public Integer getLogYear() {
		return logYear;
	}
	public void setLogYear(Integer logYear) {
		this.logYear = logYear;
	}
	public Integer getLogMonth() {
		return logMonth;
	}
	public void setLogMonth(Integer logMonth) {
		this.logMonth = logMonth;
	}
	public Integer getLogDay() {
		return logDay;
	}
	public void setLogDay(Integer logDay) {
		this.logDay = logDay;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Integer getMark() {
		return mark;
	}
	public void setMark(Integer mark) {
		this.mark = mark;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
}

package com.wp.bean;

import java.util.Date;

/**
 * 
 * 
 * 工程名:blog
 * 包名:com.wp.bean
 * 类名:Channel	栏目实体类
 * 创建人:wenpeng
 * Email:1091654568@qq.com
 * 时间：2017年3月31日-下午2:29:24
 */
public class Channel implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	private Integer id;/* 主键 */
	private String name;
	private String description;
	private String model;
	private String url;
	private Date createTime;// 创建时间
	private Date updateTime;// 创建时间
	private Integer userId;// 用户ID
	private Integer sort;// 排序
	private Integer isDelete;// 1删除0未删除
	private Integer status;// 0未发布1发布
	private Integer parentId;// 父栏目id
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Integer getSort() {
		return sort;
	}
	public void setSort(Integer sort) {
		this.sort = sort;
	}
	public Integer getIsDelete() {
		return isDelete;
	}
	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Integer getParentId() {
		return parentId;
	}
	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}
	
}
package com.wp.bean;
import java.util.Date;

/**
 * 
 * 
 * 角色模块
 * 类名:Role
 * 创建人:wenpeng
 * Email:1091654568@qq.com
 * 时间：2017年04月02日-下午10:13:58
 */
public class Role implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	private Integer id;/* 主键 */
	private String name;// 名字
	private String description;// 描述
	private Date createTime;// 创建时间
	private Date updateTime;// 更新时间
	private Integer isDelete;// 0未删除1删除
	private Integer userId;//
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
	public Integer getIsDelete() {
		return isDelete;
	}
	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}


}


package com.wp.bean;
import java.util.Date;

/**
 * 
 * 
 * 用户模块
 * 类名:User
 * 创建人:wenpeng
 * Email:1091654568@qq.com
 * 时间：2017年04月02日-下午10:14:53
 */
public class User implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	//id
	private Integer id;
	//用户名
	private String username;
	//用户密码
	private String password;
	//创建时间
	private Date createTime;
	//更新时间
	private Date updateTime;
	//登陆限制 0禁止登陆1不禁止
	private Integer forbiden;
	//删除状态0未删除1删除
	private Integer isDelete;
	//性别 0女1男
	private Integer male;
	//年龄
	private Integer age;
	//生日
	private Date birthday;
	//地址
	private String address;
	//邮箱
	private String email;
	//电话
	private String telephone;
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
	public Integer getForbiden() {
		return forbiden;
	}
	public void setForbiden(Integer forbiden) {
		this.forbiden = forbiden;
	}
	public Integer getIsDelete() {
		return isDelete;
	}
	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}
	public Integer getMale() {
		return male;
	}
	public void setMale(Integer male) {
		this.male = male;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
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


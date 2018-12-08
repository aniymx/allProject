package com.wp.bean;

import java.util.Date;

/**
 * 
 * 
 * 工程名:blog
 * 包名:com.wp.bean
 * 类名:Content	内容实体类
 * 创建人:wenpeng
 * Email:1091654568@qq.com
 * 时间：2017年3月31日-下午2:29:24
 */
public class Content implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	private Integer id;/* 主键 */
	private String title;// 标题
	private String subTitle;// 子标题
	private String content;// 内容
	private Date createTime;// 创建时间
	private Date updateTime;// 更新时间
	private String staticLink;// 静态连接
	private String tag;// 标签
	private String img;// 封面图片
	private Integer isTop;// 是否置顶1置顶0未置顶
	private Integer isDelete;// 0未删除1删除
	private Integer status;// 0未发布1发布
	private Integer channelId;// 类型  1网站首页2 Web前端3 Java4  网路技5  慢生6 关于本站

	private Integer push;// 是否精华推荐内容0否1是
	private Integer isComment;// 是否允许评论1允许0不允许
	private Integer hits;// 点击数
	private Integer loves;// 喜欢数量
	private Integer comments;// 评论数据
	private String keywords;// seo关键字
	private String description;// seo描述
	private String author;//作者
	private String channelName;
	private Integer previousId;  //前一篇的ID
	private String previousTitle; //前一篇的标题
	private String previousLink; //前一篇的地址
	private Integer nextId; //后一篇的ID
	private String nextTitle; //后一篇的标题
	private String nextLink; //后一篇的地址
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getSubTitle() {
		return subTitle;
	}
	public void setSubTitle(String subTitle) {
		this.subTitle = subTitle;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
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
	public String getStaticLink() {
		return staticLink;
	}
	public void setStaticLink(String staticLink) {
		this.staticLink = staticLink;
	}
	public String getTag() {
		return tag;
	}
	public void setTag(String tag) {
		this.tag = tag;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public Integer getIsTop() {
		return isTop;
	}
	public void setIsTop(Integer isTop) {
		this.isTop = isTop;
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
	public Integer getChannelId() {
		return channelId;
	}
	public void setChannelId(Integer channelId) {
		this.channelId = channelId;
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
	public Integer getHits() {
		return hits;
	}
	public void setHits(Integer hits) {
		this.hits = hits;
	}
	public Integer getLoves() {
		return loves;
	}
	public void setLoves(Integer loves) {
		this.loves = loves;
	}
	public Integer getComments() {
		return comments;
	}
	public void setComments(Integer comments) {
		this.comments = comments;
	}
	public String getKeywords() {
		return keywords;
	}
	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getChannelName() {
		return channelName;
	}
	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}
	public Integer getPreviousId() {
		return previousId;
	}
	public void setPreviousId(Integer previousId) {
		this.previousId = previousId;
	}
	public String getPreviousTitle() {
		return previousTitle;
	}
	public void setPreviousTitle(String previousTitle) {
		this.previousTitle = previousTitle;
	}
	public String getPreviousLink() {
		return previousLink;
	}
	public void setPreviousLink(String previousLink) {
		this.previousLink = previousLink;
	}
	public Integer getNextId() {
		return nextId;
	}
	public void setNextId(Integer nextId) {
		this.nextId = nextId;
	}
	public String getNextTitle() {
		return nextTitle;
	}
	public void setNextTitle(String nextTitle) {
		this.nextTitle = nextTitle;
	}
	public String getNextLink() {
		return nextLink;
	}
	public void setNextLink(String nextLink) {
		this.nextLink = nextLink;
	}
	
}
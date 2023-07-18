package com.hypothesis.cms.dto;

import java.util.Date;

public class ArticleVersionDTO {

	private Integer articleVersionId;
	private Integer articleId;
	private Integer userId;
	private Integer version;
	private String content;
	private Date updatedDate;
	private Date createdDate;
	
	public Integer getArticleVersionId() {
		return articleVersionId;
	}

	public void setArticleVersionId(Integer articleVersionId) {
		this.articleVersionId = articleVersionId;
	}

	public Integer getArticleId() {
		return articleId;
	}

	public void setArticleId(Integer articleId) {
		this.articleId = articleId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	
	
}
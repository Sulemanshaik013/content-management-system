package com.hypothesis.cms.dto;

public class ArticleDto {

	private String title;
	private String content;
	private String author;
	private long categoryid;
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public long getCategoryid() {
		return categoryid;
	}
	public void setCategoryid(long categoryid) {
		this.categoryid = categoryid;
	}

}
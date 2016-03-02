package com.supwisdom.service.entity;

public class JwContent {

	private String author;
	private String category;
	/** 新闻标题 **/
	private String title;
	/** 新闻发布时间 **/
	private String time;
	/** 新闻内容 **/
	private String text;
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}

}

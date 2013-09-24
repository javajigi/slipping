package net.slipp.domain.qna;

import java.util.Date;

import net.slipp.domain.user.User;

public class Question {
	private User writer;
	
	private String title;

	private String contents;

	private Date createdDate;

	public Question(User writer, String title, String contents) {
		this.writer = writer;
		this.title = title;
		this.contents = contents;
		this.createdDate = new Date();
	}

	public User getWriter() {
		return writer;
	}

	public String getTitle() {
		return title;
	}
	
	public String getContents() {
		return contents;
	}
	
	public Date getCreatedDate() {
		return createdDate;
	}
	
	@Override
	public String toString() {
		return "Question [writer=" + writer + ", title=" + title + ", contents="
				+ contents + ", createdDate=" + createdDate + "]";
	}
}

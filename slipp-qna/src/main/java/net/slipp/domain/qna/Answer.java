package net.slipp.domain.qna;

import java.util.Date;

import net.slipp.domain.user.User;

public class Answer {
	private User writer;
	
	private String contents;
	
	private Date createdDate;
	
	public Answer(User writer, String contents) {
		this.writer = writer;
		this.contents = contents;
		this.createdDate = new Date();
	}

	public User getWriter() {
		return writer;
	}

	public String getContents() {
		return contents;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	@Override
	public String toString() {
		return "Answer [writer=" + writer + ", contents=" + contents + ", createdDate="
				+ createdDate + "]";
	}
}

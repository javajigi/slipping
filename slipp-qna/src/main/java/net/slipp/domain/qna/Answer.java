package net.slipp.domain.qna;

import java.util.Date;

public class Answer {
	private Long answerId;
	
	private String writerId;
	
	private String writerName;
	
	private String contents;
	
	private Date createdDate;

	private Date updatedDate;
	
	public Long getAnswerId() {
		return answerId;
	}

	public void setAnswerId(Long answerId) {
		this.answerId = answerId;
	}

	public String getWriterId() {
		return writerId;
	}

	public void setWriterId(String writerId) {
		this.writerId = writerId;
	}

	public String getWriterName() {
		return writerName;
	}

	public void setWriterName(String writerName) {
		this.writerName = writerName;
	}

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	@Override
	public String toString() {
		return "Answer [answerId=" + answerId + ", writerId=" + writerId + ", writerName=" + writerName + ", contents="
				+ contents + ", createdDate=" + createdDate + ", updatedDate=" + updatedDate + "]";
	}
}

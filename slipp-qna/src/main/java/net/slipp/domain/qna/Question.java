package net.slipp.domain.qna;

import java.util.Date;

import net.slipp.domain.user.User;

public class Question {
	private Long questionId;

	private String writerId;
	
	private String writerName;

	private String title;

	private String contents;

	private Date createdDate;

	private int answerCount = 0;

	public Question() {
	}
	
	Question(String writerId, String writerName, String title, String contents) {
		this.writerId = writerId;
		this.writerName = writerName;
		this.title = title;
		this.contents = contents;
	}

	public int getAnswerCount() {
		return answerCount;
	}
	
	public void setContents(String contents) {
		this.contents = contents;
	}
	
	public String getContents() {
		return contents;
	}

	public Long getQuestionId() {
		return questionId;
	}

	public void setQuestionId(Long questionId) {
		this.questionId = questionId;
	}
	
	public void writedBy(User user) {
		this.writerId = user.getUserId();
		this.writerName = user.getName();
	}

	public String getWriterId() {
		return writerId;
	}

	public String getWriterName() {
		return writerName;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setAnswerCount(int answerCount) {
		this.answerCount = answerCount;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public void update(Question newQuestion) {
		this.title = newQuestion.title;
		this.contents = newQuestion.contents;
	}

	@Override
	public String toString() {
		return "Question [questionId=" + questionId + ", writerId=" + writerId + ", writerName=" + writerName
				+ ", title=" + title + ", contents=" + contents + ", createdDate=" + createdDate + ", answerCount="
				+ answerCount + "]";
	}
}

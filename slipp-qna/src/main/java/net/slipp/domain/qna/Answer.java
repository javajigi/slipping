package net.slipp.domain.qna;

import java.util.Collection;
import java.util.Date;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

import net.slipp.domain.user.User;

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

@Entity
public class Answer {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long answerId;
	
	@ManyToOne
	@org.hibernate.annotations.ForeignKey(name = "fk_answer_writer")
	private User writer;
	
	@ElementCollection(fetch = FetchType.LAZY)
	@CollectionTable(name = "answer_content_holder", joinColumns = @JoinColumn(name = "answer_id", unique = true))
	@org.hibernate.annotations.ForeignKey(name = "fk_answer_content_holder_answer_id")
	@Lob
	@Column(name = "contents", nullable = false)
	private Collection<String> contentsHolder;
	
	@ManyToOne
	@org.hibernate.annotations.ForeignKey(name = "fk_answer_parent_id")
	private Question question;
	
	private Date createdDate;
	
	public Answer(User writer, String contents) {
		this.writer = writer;
		this.contentsHolder = Lists.newArrayList();
		this.createdDate = new Date();
	}

	public User getWriter() {
		return writer;
	}

	private boolean isEmptyContentsHolder() {
		return contentsHolder == null || contentsHolder.isEmpty();
	}

	public String getContents() {
		if (isEmptyContentsHolder()) {
			return "";
		}

		return Iterables.getFirst(contentsHolder, "");
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	@Override
	public String toString() {
		return "Answer [writer=" + writer + ", contents=" + getContents() + ", createdDate="
				+ createdDate + "]";
	}
}

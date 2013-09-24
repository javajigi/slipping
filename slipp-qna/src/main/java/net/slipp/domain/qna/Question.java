package net.slipp.domain.qna;

import java.util.Date;
import java.util.Set;

import com.google.common.collect.Sets;

import net.slipp.domain.user.User;

public class Question {
	private User writer;
	
	private String title;

	private String contents;

	private Date createdDate;

	private Set<Tag> tags = Sets.newHashSet();

	public Question(User writer, String title, String contents, Set<Tag> tags) {
		this.writer = writer;
		this.title = title;
		this.contents = contents;
		processTags(tags);
		this.tags = tags;
		this.createdDate = new Date();
	}

	private void processTags(Set<Tag> newTags) {
		Set<Tag> taggedTags = Sets.difference(newTags, tags);
		for (Tag tag : taggedTags) {
			tag.tagged();
		}
		
		Set<Tag> detaggedTags = Sets.difference(tags, newTags);
		for (Tag tag : detaggedTags) {
			tag.detagged();
		}
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
	
	public Set<Tag> getTags() {
		return tags;
	}
	
	public void update(String title, String contents, Set<Tag> updateTags) {
		processTags(updateTags);
		this.tags = updateTags;
	}
	
	@Override
	public String toString() {
		return "Question [writer=" + writer + ", title=" + title + ", contents="
				+ contents + ", createdDate=" + createdDate + "]";
	}
}

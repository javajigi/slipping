package net.slipp.domain.qna;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import net.slipp.domain.user.User;

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

@Entity
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long questionId;
	
    @ManyToOne
    @org.hibernate.annotations.ForeignKey(name = "fk_question_writer")
	private User writer;
	
    @Column(name = "title", length = 100, nullable = false)
	private String title;

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "question_content_holder", joinColumns = @JoinColumn(name = "question_id", unique = true))
    @org.hibernate.annotations.ForeignKey(name = "fk_question_content_holder_question_id")
    @Lob
    @Column(name = "contents", nullable = false)
    private Collection<String> contentsHolder;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_date", nullable = false, updatable = false)
	private Date createdDate;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "question_tag", joinColumns = @JoinColumn(name = "question_id"), inverseJoinColumns = @JoinColumn(name = "tag_id"))
    @org.hibernate.annotations.ForeignKey(name = "fk_question_tag_question_id", inverseName = "fk_question_tag_tag_id")
	private Set<Tag> tags = Sets.newHashSet();
    
    @OneToMany(mappedBy = "question", fetch = FetchType.LAZY)
    @OrderBy("answerId ASC")
    private List<Answer> answers;
    
    private int showCount = 0;

	public Question(User writer, String title, String contents, Set<Tag> tags) {
		this.writer = writer;
		this.title = title;
		this.contentsHolder = Lists.newArrayList(contents);
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
	
	public Set<Tag> getTags() {
		return tags;
	}
	
	public void update(String title, String contents, Set<Tag> updateTags) {
		processTags(updateTags);
		this.tags = updateTags;
	}
	
	public void showed() {
		this.showCount++;
	}
	
	public int getShowCount() {
		return showCount;
	}
	
	@Override
	public String toString() {
		return "Question [writer=" + writer + ", title=" + title + ", contents="
				+ getContents() + ", createdDate=" + createdDate + "]";
	}
}

package net.slipp.domain.qna;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.Set;

import net.slipp.domain.user.UserTest;

import org.junit.Test;

import com.google.common.collect.Sets;

public class QuestionTest {
	@Test
	public void createQuestion() throws Exception {
		Set<Tag> tags = Sets.newHashSet(Tag.pooledTag("java"), Tag.newTag("ant"));
		Question question = new Question(UserTest.JAVAJIGI, "title", "contents", tags);
		for (Tag each : question.getTags()) {
			assertThat(each.getTaggedCount(), is(1));
		}
	}
	
	@Test
	public void updateQuestion() throws Exception {
		Tag java = Tag.pooledTag("java");
		Tag ant = Tag.newTag("ant");
		Set<Tag> tags = Sets.newHashSet(java, ant);
		Question question = new Question(UserTest.JAVAJIGI, "title", "contents", tags);
		
		Tag eclipse = Tag.newTag("eclipse");
		Set<Tag> updateTags = Sets.newHashSet(java, eclipse);
		question.update("update title", "update contents", updateTags);
		
		assertThat(java.getTaggedCount(), is(1));
		assertThat(ant.getTaggedCount(), is(0));
		assertThat(eclipse.getTaggedCount(), is(1));
	}
}

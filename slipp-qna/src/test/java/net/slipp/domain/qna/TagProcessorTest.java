package net.slipp.domain.qna;

import static org.junit.Assert.assertTrue;

import java.util.Set;

import org.junit.Before;
import org.junit.Test;

public class TagProcessorTest {
	private TagRepository repository;
	
	@Before
	public void setup() {
		repository = new MockTagRepository();
	}
	
	@Test
	public void processTags_plainTag_isNull() throws Exception {
		TagProcessor dut = new TagProcessor(repository);
		Set<Tag> newTags = dut.processTags(null);
		assertTrue(newTags.isEmpty());
	}
	
	@Test
	public void processTags_pooledTags() throws Exception {
		TagProcessor dut = new TagProcessor(repository);
		Set<Tag> newTags = dut.processTags("java eclipse");
		assertTrue(newTags.contains(Tag.pooledTag("java")));
		assertTrue(newTags.contains(Tag.pooledTag("eclipse")));
	}
	
	@Test
	public void processTags_newTags() throws Exception {
		TagProcessor dut = new TagProcessor(repository);
		Set<Tag> newTags = dut.processTags("java ant");
		assertTrue(newTags.contains(Tag.pooledTag("java")));
		assertTrue(newTags.contains(Tag.newTag("ant")));
	}
}
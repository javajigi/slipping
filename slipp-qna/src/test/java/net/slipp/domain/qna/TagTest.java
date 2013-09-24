package net.slipp.domain.qna;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class TagTest {

	@Test
	public void createPooledTag() {
		Tag java = Tag.pooledTag("java");
		assertTrue(java.isPooled());
	}
	
	@Test
	public void createNewTag() {
		Tag java = Tag.newTag("java");
		assertFalse(java.isPooled());
	}
}

package net.slipp.domain.qna;

import java.util.Set;

import com.google.common.collect.Sets;

public class TagProcessor {
	private TagRepository repository;

	public TagProcessor(TagRepository repository) {
		this.repository = repository;
	}

	public Set<Tag> processTags(String plainTags) {
		Set<Tag> tags = Sets.newHashSet();
		for (String each : parsePlainTags(plainTags)) {
			Tag tag = repository.findByName(each);
			if (tag == null) {
				tag = repository.save(Tag.newTag(each));
			} 
			tags.add(tag);
		}
		return tags;
	}

	private String[] parsePlainTags(String plainTags) {
		if (plainTags == null) {
			return new String[]{};
		}
		
		return plainTags.split(" ");
	}

}
package net.slipp.domain.qna;

public interface TagRepository {

	Tag findByName(String name);

	Tag save(Tag newTag);

}

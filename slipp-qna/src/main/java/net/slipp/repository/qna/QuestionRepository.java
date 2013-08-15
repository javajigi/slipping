package net.slipp.repository.qna;

import net.slipp.domain.qna.Question;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface QuestionRepository extends PagingAndSortingRepository<Question, Long>{

}

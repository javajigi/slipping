package net.slipp.domain.qna;

import javax.annotation.Resource;

import net.slipp.domain.user.User;
import net.slipp.repository.qna.QuestionRepository;
import net.slipp.repository.qna.TagRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("qnaService")
@Transactional
public class QnaService {
	@Resource (name = "tagRepository")
	private TagRepository tagRepository;
	
	@Resource (name = "questionRepository")
	private QuestionRepository questionRepository;
	
	public void createQuestion(User user, Question question) {
		question.writedBy(user);
		question.initializeTags(tagRepository);
		questionRepository.save(question);
	}
	
	public void updateQuestion(User user, Question newQuestion) {
		Question question = questionRepository.findOne(newQuestion.getQuestionId());
		question.writedBy(user);
		question.update(newQuestion);
		question.initializeTags(tagRepository);
		questionRepository.save(question);
	}
	
	public Iterable<Question> findsQuestion() {
		return questionRepository.findAll();
	}
	
	public Page<Question> findsQuestion(Pageable page) {
		return questionRepository.findAll(page);
	}
	
	public Question findByQuestionId(Long id) {
		return questionRepository.findOne(id);
	}
	
	public Iterable<Tag> findsTag() {
		return tagRepository.findAll();
	}

}

package net.slipp.web.qna;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.slipp.domain.qna.QnaService;
import net.slipp.domain.qna.Question;
import net.slipp.domain.user.User;
import net.slipp.support.web.argumentresolver.LoginUser;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/qna")
public class QnaController {
	private static final Logger logger = LoggerFactory.getLogger(QnaController.class);

	private static final int DEFAULT_PAGE_NO = 1;
	private static final int DEFAULT_PAGE_SIZE = 10;

	@Resource(name = "qnaService")
	private QnaService qnaService;

	@RequestMapping("")
	public String index(Model model) {
		model.addAttribute("questions",
				qnaService.findsQuestion(createPageableForList(DEFAULT_PAGE_NO, DEFAULT_PAGE_SIZE)));
		model.addAttribute("tags", qnaService.findsTag());
		return "qna/list";
	}

	private Pageable createPageableForList(int page, int size) {
		if (page <= 0) {
			page = 0;
		} else {
			page--;
		}

		Sort sort = new Sort(Direction.DESC, "createdDate");
		return new PageRequest(page, size, sort);
	}

	@RequestMapping("/form")
	public String createForm(@LoginUser User user, Model model) {
		model.addAttribute(new Question());
		return "qna/form";
	}

	@RequestMapping(value = "", method = RequestMethod.POST)
	public String create(@LoginUser User user, HttpServletRequest request, Question question) {
		logger.debug("Question : {}", question);

		qnaService.createQuestion(user, question);
		return "redirect:/qna";
	}

	@RequestMapping("/{id}/form")
	public String updateForm(@LoginUser User user, @PathVariable Long id, HttpServletRequest request, Model model) {
		if (user == null) {
			return "redirect:/user/login/form";
		}
		model.addAttribute("question", qnaService.findByQuestionId(id));
		return "qna/form";
	}

	@RequestMapping(value = "", method = RequestMethod.PUT)
	public String update(@LoginUser User user, Question question) {
		logger.debug("Question : {}", question);
		qnaService.updateQuestion(user, question);
		return "redirect:/qna";
	}

	@RequestMapping("{id}")
	public String show(@PathVariable Long id, Model model) {
		model.addAttribute("question", qnaService.findByQuestionId(id));
		model.addAttribute("tags", qnaService.findsTag());
		return "qna/show";
	}
}

package net.slipp.web.user;

import javax.servlet.http.HttpSession;

import net.slipp.domain.user.User;
import net.slipp.service.user.PasswordMismatchException;
import net.slipp.service.user.UserService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/users")
public class UserController {
	private static Logger log = LoggerFactory.getLogger(UserController.class);

	private UserService userService = new UserService();

	@RequestMapping("/form")
	public String joinForm(Model model) throws Exception {
		model.addAttribute("user", new User());
		return "user/form";
	}

	@RequestMapping(value = "", method = RequestMethod.POST)
	public String join(User user) throws Exception {
		log.debug("user : {}", user);
		userService.join(user);
		return "redirect:/";
	}

	@RequestMapping("/login/form")
	public String loginForm() throws Exception {
		return "user/login";
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(String userId, String password, HttpSession session) throws Exception {
		try {
			User user = userService.login(userId, password);
			session.setAttribute("loginUser", user);
			return "redirect:/";
		} catch (PasswordMismatchException e) {
			return "user/login";
		}
	}

	@RequestMapping("/logout")
	public String logout(HttpSession session) throws Exception {
		session.removeAttribute("loginUser");
		return "redirect:/";
	}

	@RequestMapping("/{userId}/form")
	public String updateForm(@PathVariable String userId, Model model) throws Exception {
		User user = userService.findByUserId(userId);
		model.addAttribute("user", user);
		return "user/form";
	}

	@RequestMapping(value = "/{userId}", method = RequestMethod.POST)
	public String update(@PathVariable String userId, User user, Model model) throws Exception {
		log.debug("user : {}", user);
		try {
			userService.update(userId, user);
			return "redirect:/";
		} catch (PasswordMismatchException e) {
			model.addAttribute("user", user);
			model.addAttribute("errorMessage", "비밀번호가 틀립니다. 비밀번호 확인해 주세요.");
			return "user/form";
		}
	}
}

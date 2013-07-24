package net.slipp.web.user;

import javax.servlet.http.HttpSession;

import net.slipp.domain.user.User;
import net.slipp.service.user.PasswordMismatchException;
import net.slipp.service.user.UserService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/users")
public class UserController {
	private static Logger log = LoggerFactory.getLogger(UserController.class);
	
	private UserService userService = new UserService();
	
	@RequestMapping("/form")
	public String joinForm() throws Exception {
		return "user/join";
	}
	
	@RequestMapping(value="", method=RequestMethod.POST)
	public String join(User user) throws Exception {
		log.debug("user : {}", user);
		userService.join(user);
		return "redirect:/";
	}
	
	@RequestMapping("/login/form")
	public String loginForm() throws Exception {
		return "user/login";
	}
	
	@RequestMapping(value="/login", method=RequestMethod.POST)
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
}

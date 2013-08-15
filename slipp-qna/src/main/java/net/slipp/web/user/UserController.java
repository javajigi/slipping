package net.slipp.web.user;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import net.slipp.domain.user.ExistedUserException;
import net.slipp.domain.user.PasswordMismatchException;
import net.slipp.domain.user.User;
import net.slipp.domain.user.UserService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/user")
public class UserController {
	@Resource(name = "userService")
	private UserService userService;

	@RequestMapping("")
	public String index(Model model) {
		model.addAttribute("users", userService.finds());
		return "user/index";
	}

	@RequestMapping("/form")
	public String createForm(Model model, HttpSession session) {
		if ( !isLogin(session)) {
			return "redirect:/user/login/form";
		}
		model.addAttribute("user", new User());
		return "user/form";
	}

	@RequestMapping(value = "", method = RequestMethod.POST)
	public String create(User user, Model model, HttpSession session) {
		if ( !isLogin(session)) {
			return "redirect:/user/login/form";
		}
		
		try {
			userService.create(user);
		} catch (ExistedUserException e) {
		}
		return "redirect:/user";
	}

	@RequestMapping("/{userId}/form")
	public String updateForm(@PathVariable String userId, Model model, HttpSession session) {
		if ( !isLogin(session)) {
			return "redirect:/user/login/form";
		}
		
		model.addAttribute("user", userService.findUser(userId));
		return "user/form";
	}

	@RequestMapping(value = "", method = RequestMethod.PUT)
	public String update(User user, Model model, HttpSession session) {
		if ( !isLogin(session)) {
			return "redirect:/user/login/form";
		}
		
		userService.update(user);
		return "redirect:/user";
	}
	
	@RequestMapping("/login/form")
	public String loginForm(Model model) {
		return "user/login";
	}
	
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public String login(String userId, String password, HttpSession session) {
		try {
			userService.login(userId, password);
			session.setAttribute("user", userService.findUser(userId));
		} catch (PasswordMismatchException e) {
			return "user/login";
		}
		return "redirect:/";
	}
	
	private boolean isLogin(HttpSession session) {
		if ( session.getAttribute("user") == null ) {
			return false;
		}
		return true;
	}
}

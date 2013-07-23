package net.slipp.web.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.slipp.domain.user.User;
import net.slipp.service.user.UserService;
import net.slipp.support.web.Controller;

public class UserJoinController implements Controller {
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		User user = new User(
				request.getParameter("userId"), 
				request.getParameter("password"),
				request.getParameter("name"),
				request.getParameter("email"));
		UserService userService = new UserService();
		userService.join(user);
		return "redirect:/";
	}
}

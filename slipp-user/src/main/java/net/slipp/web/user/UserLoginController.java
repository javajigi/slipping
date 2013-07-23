package net.slipp.web.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.slipp.domain.user.User;
import net.slipp.service.user.PasswordMismatchException;
import net.slipp.service.user.UserService;
import net.slipp.support.web.Controller;

public class UserLoginController implements Controller {
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String userId = request.getParameter("userId");
		String password = request.getParameter("password");
		UserService userService = new UserService();

		try {
			User user = userService.login(userId, password);
			HttpSession session = request.getSession();
			session.setAttribute("loginUser", user);
			return "redirect:/";
		} catch (PasswordMismatchException e) {
			request.setAttribute("errorMessage", e.getMessage());
			return "/user/login.jsp";
		}
	}
}

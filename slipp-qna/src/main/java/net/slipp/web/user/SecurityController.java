package net.slipp.web.user;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/security")
public class SecurityController {
	@RequestMapping("/form")
	public String createForm(Model model) {
		return "security/login";
	}
}

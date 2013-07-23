package net.slipp.support.web;

import java.util.HashMap;
import java.util.Map;

import net.slipp.web.user.*;

public class HandlerMapping {
	private Map<String, Controller> controllers = new HashMap<String, Controller>();

	public void initialize() {
		controllers.put("/user/join.do", new UserJoinController());
		controllers.put("/user/login.do", new UserLoginController());
		controllers.put("/user/logout.do", new UserLogoutController());
	}
	
	public Controller findController(String url) {
		return controllers.get(url);
	}
}

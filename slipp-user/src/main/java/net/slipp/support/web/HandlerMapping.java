package net.slipp.support.web;

import java.util.HashMap;
import java.util.Map;

import net.slipp.web.user.UserJoinController;

public class HandlerMapping {
	private Map<String, Controller> controllers = new HashMap<String, Controller>();

	public void initialize() {
		controllers.put("/user/join.do", new UserJoinController());
	}
	
	public Controller findController(String url) {
		return controllers.get(url);
	}
}

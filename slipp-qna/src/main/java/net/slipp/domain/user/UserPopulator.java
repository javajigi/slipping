package net.slipp.domain.user;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import net.slipp.repository.user.UserRepository;

import org.springframework.stereotype.Component;

@Component
public class UserPopulator {
	@Resource (name = "userRepository")
	private UserRepository userRepository;
	
	@PostConstruct
	public void populate() {
		userRepository.save(new User("javajigi", "password", "박재성", "javajigi@slipp.net", true));
	}
}

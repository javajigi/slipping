package net.slipp.service.user;

import java.sql.SQLException;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import net.slipp.dao.user.UserDao;
import net.slipp.domain.user.User;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class UserService {
	private static Logger log = LoggerFactory.getLogger(UserService.class);
	
	@PostConstruct
	public void initialize() {
		log.debug("initialize");
	}
	
	@PreDestroy
	public void destroy() {
		log.debug("destroy");
	}	

	public User join(User user) throws SQLException, ExistedUserException {
		log.debug("User : {}", user);
		UserDao userDao = new UserDao();
		User existedUser = userDao.findByUserId(user.getUserId());
		if (existedUser != null) {
			throw new ExistedUserException(user.getUserId());
		}

		userDao.insert(user);
		return user;
	}

	public User login(String userId, String password) throws SQLException, PasswordMismatchException {
		UserDao userDao = new UserDao();
		User user = userDao.findByUserId(userId);
		if (user == null) {
			throw new PasswordMismatchException();
		}
		
		if (!user.matchPassword(password)) {
			throw new PasswordMismatchException();
		}

		return user;
	}

	public User findByUserId(String userId) throws SQLException {
		UserDao userDao = new UserDao();
		return userDao.findByUserId(userId);
	}

	public void update(String userId, User updateUser) throws SQLException, PasswordMismatchException {
		User user = findByUserId(userId);
		if (user == null) {
			throw new NullPointerException(userId + " user doesn't existed.");
		}
		user.update(updateUser);
	}
}

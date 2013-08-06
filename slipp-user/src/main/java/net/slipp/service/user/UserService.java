package net.slipp.service.user;

import java.sql.SQLException;

import javax.annotation.Resource;

import net.slipp.dao.user.UserDao;
import net.slipp.domain.user.User;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class UserService {
	private static Logger log = LoggerFactory.getLogger(UserService.class);
	
	@Resource(name="userDao")
	private UserDao userDao;
	
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	
	public User join(User user) throws SQLException, ExistedUserException {
		log.debug("User : {}", user);
		User existedUser = userDao.findByUserId(user.getUserId());
		if (existedUser != null) {
			throw new ExistedUserException(user.getUserId());
		}

		userDao.insert(user);
		return user;
	}

	public User login(String userId, String password) throws SQLException, PasswordMismatchException {
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
		return userDao.findByUserId(userId);
	}

	public void update(String userId, User updateUser) throws SQLException, PasswordMismatchException {
		User user = findByUserId(userId);
		if (user == null) {
			throw new NullPointerException(userId + " user doesn't existed.");
		}
		user.update(updateUser);
		userDao.update(user);
	}
}

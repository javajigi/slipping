package net.slipp.service.user;

import java.sql.SQLException;

import javax.annotation.Resource;

import net.slipp.dao.user.UserDao;
import net.slipp.domain.audit.AuditObject;
import net.slipp.domain.user.User;
import net.slipp.service.audit.AuditService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class UserService {
	private static Logger log = LoggerFactory.getLogger(UserService.class);
	private static final String CREATE_ACTION = "추가";
	private static final String UPDATE_ACTION = "수정";
	private static final String LOGIN_TRY = "로그인 시도";
	private static final String LOGIN_SUCCESS = "로그인 성공";
	private static final String LOGIN_FAILED = "로그인 실패";
	
	@Resource(name="userDao")
	private UserDao userDao;
	
	@Resource(name="auditService")
	private AuditService auditService;
	
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	
	public User join(User user) throws SQLException, ExistedUserException {
		log.debug("User : {}", user);
		auditService.log(new AuditObject(user.getUserId(), CREATE_ACTION));
		
		User existedUser = userDao.findByUserId(user.getUserId());
		if (existedUser != null) {
			throw new ExistedUserException(user.getUserId());
		}

		userDao.insert(user);
		return user;
	}

	public User login(String userId, String password) throws SQLException, PasswordMismatchException {
		auditService.log(new AuditObject(userId, LOGIN_TRY));
		User user = userDao.findByUserId(userId);
		if (user == null) {
			auditService.log(new AuditObject(userId, LOGIN_FAILED));
			throw new PasswordMismatchException();
		}
		
		if (!user.matchPassword(password)) {
			auditService.log(new AuditObject(userId, LOGIN_FAILED));
			throw new PasswordMismatchException();
		}

		auditService.log(new AuditObject(userId, LOGIN_SUCCESS));
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
		auditService.log(new AuditObject(user.getUserId(), UPDATE_ACTION));
	}
}

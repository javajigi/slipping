package net.slipp.dao.user;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import net.slipp.domain.user.User;

@Repository("memoryUserDao")
public class MemoryUserDao implements UserDao {
	private static Map<String, User> users = new HashMap<String, User>();
	
	public void insert(User user) throws SQLException {
		users.put(user.getUserId(), user);
	}

	public User findByUserId(String userId) throws SQLException {
		return users.get(userId);
	}
}

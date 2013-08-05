package net.slipp.dao.user;

import java.sql.SQLException;

import net.slipp.domain.user.User;

public interface UserDao {
	void insert(User user) throws SQLException;
	
	User findByUserId(String userId) throws SQLException;
}

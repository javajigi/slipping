package net.slipp.dao.user;

import java.sql.SQLException;

import org.springframework.stereotype.Repository;

import net.slipp.domain.user.User;

@Repository("jdbcUserDao")
public class JdbcUserDao implements UserDao {

	@Override
	public void insert(User user) throws SQLException {
		// insert into user values();
	}

	@Override
	public User findByUserId(String userId) throws SQLException {
		// select userId, name, password, email from user;
		return null;
	}

}

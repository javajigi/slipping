package net.slipp.dao.user;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import net.slipp.domain.user.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.stereotype.Repository;

@Repository("userDao")
public class SpringJdbcUserDao extends JdbcDaoSupport implements UserDao {
	@Autowired
	private DataSource dataSource;

	@PostConstruct
	public void initialize() {
		setDataSource(dataSource);
		ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
		populator.addScript(new ClassPathResource("user.sql"));
		DatabasePopulatorUtils.execute(populator, dataSource);		
	}

	@Override
	public void insert(User user) throws SQLException {
		String sql = "INSERT INTO USERS VALUES (?, ?, ?, ?)";
		getJdbcTemplate().update(sql, user.getUserId(), user.getPassword(), user.getName(), user.getEmail());
	}

	@Override
	public void update(User user) throws SQLException {
		String sql = "UPDATE USERS SET name=?, email=? WHERE userId=?";
		getJdbcTemplate().update(sql, user.getName(), user.getEmail(), user.getUserId());

	}

	@Override
	public User findByUserId(String userId) throws SQLException {
		String sql = "SELECT userId, password, name, email FROM USERS WHERE userid=?";
		RowMapper<User> rowMapper = new RowMapper<User> () {
			@Override
			public User mapRow(ResultSet rs, int rowNum) throws SQLException {
				return new User(
						rs.getString("userId"), 
						rs.getString("password"), 
						rs.getString("name"),
						rs.getString("email"));
			}
			
		};
		return DataAccessUtils.uniqueResult(getJdbcTemplate().query(sql, rowMapper, userId));
	}
}

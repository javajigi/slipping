package net.slipp.dao.user;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import net.slipp.domain.user.User;

import org.junit.Test;

public class UserDaoTest {

	@Test
	public void crud() throws Exception {
		User expected = new User("userId", "password", "name", "javajigi@email.com");
		UserDao userDao = new UserDao();
		userDao.insert(expected);

		User actual = userDao.findByUserId(expected.getUserId());
		assertThat(actual, is(expected));
	}

}

package net.slipp.domain.user;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import net.slipp.domain.user.User;

import org.junit.Test;

public class UserTest {
	public static User JAVAJIGI = new User("javajigi", "password", "name", "javajigi@slipp.net", true);
	
	@Test
	public void loginSuccess() throws Exception {
		User expected = new User("userId", "password", "name", "javajigi@slipp.net", true);
		assertThat(expected.isMatchPassword("password"), is(true));
	}
	
	@Test
	public void loginFailure() throws Exception {
		User expected = new User("userId", "password", "name", "javajigi@slipp.net", true);
		assertThat(expected.isMatchPassword("password2"), is(false));
	}
}

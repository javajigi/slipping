package net.slipp.domain.user;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Test;

public class UserTest {

	@Test
	public void 비밀번호_일치() {
		User user = new User("userId", "password", "name", "javajigi@email.com");
		assertThat(user.matchPassword("password"), is(true)); 
	}
	
	@Test
	public void 비밀번호_불일치() throws Exception {
		User user = new User("userId", "password", "name", "javajigi@email.com");
		assertThat(user.matchPassword("password2"), is(false)); 
	}
	
	@Test
	public void 비밀번호_불일치_null값() throws Exception {
		User user = new User("userId", "password", "name", "javajigi@email.com");
		assertThat(user.matchPassword(null), is(false));
		
		user = new User("userId", null, "name", "javajigi@email.com");
		assertThat(user.matchPassword("password"), is(false));
	}
}

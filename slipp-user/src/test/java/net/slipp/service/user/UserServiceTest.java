package net.slipp.service.user;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import net.slipp.domain.user.User;

import org.junit.Test;

public class UserServiceTest {
	@Test
	public void 정상적으로_회원가입한다() throws Exception {
		UserService userService = new UserService();
		User user = new User("userId1", "password", "name", "javajigi@email.com");
		User joinedUser = userService.join(user);
		assertThat(joinedUser, is(user));
	}
	
	@Test(expected=ExistedUserException.class)
	public void 이미_존재하는_사용자_아이디로_회원가입한다() throws Exception {
		UserService userService = new UserService();
		User user1 = new User("userId2", "password", "name", "javajigi@email.com");
		User joinedUser = userService.join(user1);
		assertThat(joinedUser, is(user1));
		
		User user2 = new User("userId2", "password2", "name2", "javajigi@email.com2");
		userService.join(user2);
	}
	
	@Test
	public void 사용자_조회() throws Exception {
		User user3 = new User("userId3", "password2", "name2", "javajigi@email.com2");
		UserService userService = new UserService();
		userService.join(user3);
		User user = userService.findByUserId(user3.getUserId());
		assertThat(user, is(user3));
	}
	
	@Test
	public void 로그인_성공() throws Exception {
		String userId = "admin";
		String password = "password";
		UserService userService = new UserService();
		User user = userService.login(userId, password);
		assertThat(user, is(notNullValue()));
	}
	
	@Test(expected=PasswordMismatchException.class)
	public void 로그인_비밀번호가_달라서_실패() throws Exception {
		String userId = "admin";
		String password = "password2";
		UserService userService = new UserService();
		userService.login(userId, password);
	}
	
	@Test(expected=PasswordMismatchException.class)
	public void 로그인_존재하지_않는_사용자라서_실패() throws Exception {
		String userId = "dontexisted";
		String password = "password2";
		UserService userService = new UserService();
		userService.login(userId, password);
	}
}

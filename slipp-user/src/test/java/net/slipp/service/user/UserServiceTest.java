package net.slipp.service.user;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import net.slipp.dao.user.UserDao;
import net.slipp.domain.user.User;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {
	private UserService userService;
	
	@Mock private UserDao userDao;
	
	@Before
	public void setup() {
		userService = new UserService();
		userService.setUserDao(userDao);
	}

	@Test
	public void join_success() throws Exception {
		User user = new User("userId1", "password", "name", "javajigi@email.com");
		when(userDao.findByUserId(user.getUserId())).thenReturn(null);
		User joinedUser = userService.join(user);
		verify(userDao).insert(user);
		assertThat(joinedUser, is(user));
	}
	
	@Test(expected=ExistedUserException.class)
	public void join_failed() throws Exception {
		User user1 = new User("userId2", "password", "name", "javajigi@email.com");
		when(userDao.findByUserId(user1.getUserId())).thenReturn(user1);
		User joinedUser = userService.join(user1);
		assertThat(joinedUser, is(user1));
	}
	
	@Test
	public void 사용자_조회() throws Exception {
		User user3 = new User("userId3", "password2", "name2", "javajigi@email.com2");
		when(userDao.findByUserId(user3.getUserId())).thenReturn(user3);
		User user = userService.findByUserId(user3.getUserId());
		assertThat(user, is(user3));
	}
	
	@Test
	public void 로그인_성공() throws Exception {
		String userId = "admin";
		String password = "password";
		User user = new User(userId, password, "name2", "javajigi@email.com2");
		when(userDao.findByUserId(user.getUserId())).thenReturn(user);
		User loginedUser = userService.login(userId, password);
		assertThat(loginedUser, is(notNullValue()));
	}
	
	@Test(expected=PasswordMismatchException.class)
	public void 로그인_비밀번호가_달라서_실패() throws Exception {
		String userId = "admin";
		String password = "password2";
		User user = new User(userId, password + "3", "name2", "javajigi@email.com2");
		when(userDao.findByUserId(user.getUserId())).thenReturn(user);		
		userService.login(userId, password);
	}
	
	@Test(expected=PasswordMismatchException.class)
	public void 로그인_존재하지_않는_사용자라서_실패() throws Exception {
		String userId = "dontexisted";
		String password = "password2";
		when(userDao.findByUserId(userId)).thenReturn(null);	
		userService.login(userId, password);
	}
}

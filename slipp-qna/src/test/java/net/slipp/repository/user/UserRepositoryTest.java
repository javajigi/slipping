package net.slipp.repository.user;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import net.slipp.domain.user.User;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:test-applicationContext.xml")
public class UserRepositoryTest {
	@Autowired
	private UserRepository userRepository;

	@Test
	public void crud() throws Exception {
		User user = new User("userId", "password", "name", "test@slipp.net", false);
		userRepository.save(user);
		
		User actual = userRepository.findOne(user.getUserId());
		assertThat(actual, is(user));
		
		User updatedUser = new User("userId", "password1", "name2", "test@slipp.net2", false);
		actual.update(updatedUser);
		userRepository.save(updatedUser);
		
		actual = userRepository.findOne(user.getUserId());
		assertThat(actual, is(updatedUser));
	}

}

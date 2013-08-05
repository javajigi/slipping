package net.slipp.study.di;

import static org.junit.Assert.*;

import net.slipp.service.user.UserService;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ApplicationContextTest {
	@Test
	public void isSingleton() throws Exception {
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext-slipp.xml");
		UserService userService1 = context.getBean(UserService.class);
		UserService userService2 = context.getBean(UserService.class);
		assertTrue(userService1 == userService2);
		assertTrue(userService1.equals(userService2));
	}
}

package net.slipp.support;

import static org.junit.Assert.*;

import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:infrastructure.xml")
public class ApplicationContextTest {
	@Autowired
	private DataSource dataSource;
	
	@Test
	public void empty() throws Exception {
		assertNotNull(dataSource);
	}
	
}

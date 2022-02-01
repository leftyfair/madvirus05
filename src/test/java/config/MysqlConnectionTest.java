package config;

import static org.junit.Assert.*;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = RootConfig.class)
public class MysqlConnectionTest {

	@Autowired
	private DataSource dataSource;
	
	@Test
	public void test() {
		assertNotNull(dataSource);
	}

}

package madvirus.model.auth;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import config.RootConfig;
import config.ServletConfig;
import madvirus.exception.MemberNotFoundException;
import madvirus.exception.WrongIdPasswordException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {RootConfig.class, ServletConfig.class})
@WebAppConfiguration
public class AuthServiceTest {

	@Autowired
	private AuthService authService;
	
	@Test(expected = MemberNotFoundException.class)
	public void authenticateMemberNotFoundExcetionTest() {
		authService.authenticate("hoho@hhh.com", "4442");
	}

	@Test(expected = WrongIdPasswordException.class)
	public void authenticateWrongIdPasswordExceptionTest() {
		authService.authenticate("how@how.com", "5566");
	}
	
	@Test
	public void authenticateTest() {
		AuthInfo authInfo = authService.authenticate("how@how.com", "4321");
		assertEquals("how", authInfo.getName());
	}
	
}

package madvirus.service;

import static org.junit.Assert.*;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import config.RootConfig;
import config.ServletConfig;
import madvirus.exception.DuplicateMemberException;
import madvirus.model.command.RegisterCommand;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {RootConfig.class, ServletConfig.class})
@WebAppConfiguration
public class MemberRegisterServiceTest {
	
	@Autowired
	private MemberRegisterService service;
	
	@Test(expected = DuplicateMemberException.class)
	public void registerExceptionTest() {
		RegisterCommand command = new RegisterCommand();
		command.setEmail("how@how.com");
		service.register(command);
	}

	@Test
	@Ignore
	public void registerTest() {
		RegisterCommand command = new RegisterCommand();
		command.setEmail("hell@how.com");
		command.setPassword("1234");
		command.setName("끄네");
		Long registered = service.register(command);
		assertSame(7L, registered);;
	}
}

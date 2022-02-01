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
import madvirus.dao.MemberDao;
import madvirus.exception.MemberNotFoundException;
import madvirus.exception.WrongIdPasswordException;
import madvirus.model.Member;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {RootConfig.class, ServletConfig.class})
@WebAppConfiguration
public class ChangePasswordServiceTest {

	@Autowired
	private ChangePasswordService service;
	
	@Autowired
	private MemberDao memberDao;
	
	@Test(expected = MemberNotFoundException.class)
	public void changePasswordMemberNotFoundExcetionTest() {
		service.changePassword("ho@ho.com", "1234", "1221");
	}
	
	@Test(expected = WrongIdPasswordException.class)
	public void WrongIdPasswordExceptionTest() {
		service.changePassword("how@how.com", "2222", "1234");
	}

	@Test
	@Ignore
	public void changePasswordTest() {
		service.changePassword("test@test.com", "test1234", "update1234");
		Member member = memberDao.selectByEmail("test@test.com");
		assertEquals("update1234", member.getPassword());
	}
	

}

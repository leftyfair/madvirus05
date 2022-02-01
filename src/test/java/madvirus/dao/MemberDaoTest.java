package madvirus.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import config.RootConfig;
import config.ServletConfig;
import madvirus.model.Member;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {RootConfig.class, ServletConfig.class})
@WebAppConfiguration
public class MemberDaoTest {

	@Autowired
	private MemberDao memberDao;
	
	@Test
	public void countTest() {
		System.out.println(memberDao.count());
	}

	@Test
	public void selectAllTest() {
		List<Member> members = memberDao.selectAll();
		for(Member m : members) {
			System.out.println(m);
		}
	}
	
	@Test
	public void selectByEmailTest() {
		Member member1 = memberDao.selectByEmail("how@how.com");
		Member member2 = memberDao.selectByEmail("hke@how.com");
		assertEquals("how", member1.getName());
		assertNull(member2);
	}
	@Test
	public void selectByIdTest() {
		Member member1 = memberDao.selectById(2L);
		Member member2 = memberDao.selectById(1000L);
		assertEquals("how", member1.getName());
		assertNull(member2);
	}
	
	@Test
	@Ignore
	public void insertTest() {
		Member member = new Member();
		member.setEmail("test@test.com");
		member.setName("테스트");
		member.setPassword("1234");
		member.setRegisterDateTime(LocalDateTime.now());
		Long insertedNumber = memberDao.insert(member);
		
		Member inserted = memberDao.selectById(insertedNumber);
		assertEquals("test@test.com", inserted.getEmail());
		assertEquals("테스트", inserted.getName());
		assertEquals("1234", inserted.getPassword());
	}
	
	@Test
	@Ignore
	public void updateTest() {
		Member member = memberDao.selectByEmail("test@test.com");
		member.setName("updated test");
		member.setPassword("update1234");
		memberDao.update(member);
		Member updated = memberDao.selectByEmail("test@test.com");
		assertEquals(member.getName(), updated.getName());
		assertEquals(member.getPassword(), updated.getPassword());
	}
	
	@Test
	public void selectByRegdateTest() {
		List<Member> members = memberDao.selectByRegdate(LocalDateTime.of(2022, 1, 23, 0, 0), LocalDateTime.of(2022, 02, 01, 0, 0));
		System.out.println(members);
	}
}

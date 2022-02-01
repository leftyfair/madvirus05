package madvirus.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import madvirus.dao.MemberDao;
import madvirus.exception.DuplicateMemberException;
import madvirus.model.Member;
import madvirus.model.command.RegisterCommand;

@Service
public class MemberRegisterService {

	private MemberDao memberDao;

	@Autowired
	public MemberRegisterService(MemberDao memberDao) {
		this.memberDao = memberDao;
	}
	
	public Long register(RegisterCommand registerCommand) {
		// 이메일 중복 확인 - 중복시 예외발생
		Member member = memberDao.selectByEmail(registerCommand.getEmail());
		if (member != null) throw new DuplicateMemberException("중복된 이메일 " + registerCommand.getEmail());
		// insert 메서드 호출
		Member newMember = new Member(
				registerCommand.getEmail(),
				registerCommand.getPassword(),
				registerCommand.getName(),
				LocalDateTime.now());
		return memberDao.insert(newMember);
	}
	
}

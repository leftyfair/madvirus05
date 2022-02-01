package madvirus.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import madvirus.dao.MemberDao;
import madvirus.exception.MemberNotFoundException;
import madvirus.exception.WrongIdPasswordException;
import madvirus.model.Member;

@Service
public class ChangePasswordService {

	private MemberDao memberDao;

	@Autowired
	public void setMemberDao(MemberDao memberDao) {
		this.memberDao = memberDao;
	}

	@Transactional
	public void changePassword(String email, String oldPwd, String newPwd) throws WrongIdPasswordException {
		Member member = memberDao.selectByEmail(email);
		if(member == null) throw new MemberNotFoundException("조회하신 회원정보가 없습니다 : " + email);
		member.changePassword(oldPwd, newPwd);
		memberDao.update(member);
	}
}


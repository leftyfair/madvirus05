package madvirus.model.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import madvirus.dao.MemberDao;
import madvirus.exception.MemberNotFoundException;
import madvirus.exception.WrongIdPasswordException;
import madvirus.model.Member;

@Service
public class AuthService {

	private MemberDao memberDao;

	@Autowired
	public void setMemberDao(MemberDao memberDao) {
		this.memberDao = memberDao;
	}
	
	public AuthInfo authenticate(String email, String password) {
		// 회원 조회
		Member member = memberDao.selectByEmail(email);
		// 예외
		if(member == null) throw new MemberNotFoundException(email);	
		// 비번 매칭 검사 - 틀릴시 예외
		if(!member.matchPassword(password)) throw new WrongIdPasswordException();
		// 권한정보 생성 후 리턴
		return new AuthInfo(member.getId(), member.getEmail(), member.getName());
	}
	
}

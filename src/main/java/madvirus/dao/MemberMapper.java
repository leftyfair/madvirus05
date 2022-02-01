package madvirus.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import madvirus.model.Member;

public class MemberMapper implements RowMapper<Member> {

	@Override
	public Member mapRow(ResultSet rs, int rowNum) throws SQLException {
		return new Member(
				rs.getLong("id"),
				rs.getString("email"),
				rs.getString("password"),
				rs.getString("name"),
				rs.getTimestamp("regdate").toLocalDateTime());
	}

	

}

package madvirus.dao;

import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import madvirus.model.Member;

@Repository
public class MemberDao {
	
	private JdbcTemplate jdbcTemplate;

	@Autowired
	public MemberDao(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	public int count() {
		return jdbcTemplate.queryForObject("select count(*) from member", Integer.class);
	}
	
	public List<Member> selectAll() {
		String sql = "select * from member";
		return jdbcTemplate.query(sql, new MemberMapper());
	}
	
	public Member selectByEmail(String email) {
		String sql = "select * from member where email = ?";
		List<Member> results = jdbcTemplate.query(sql, new MemberMapper(), email);
		return results.isEmpty() ? null : results.get(0);
	}
	
	public Member selectById(Long id) {
		String sql = "select * from member where id = ?";
		List<Member> results = jdbcTemplate.query(sql, new MemberMapper(), id);
		return results.isEmpty() ? null : results.get(0);
	}
	
	public Long insert(Member member) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update((con) -> {
			String sql = "insert into member(email, password, name, regdate) values(?,?,?,?)";
			PreparedStatement pstmt = con.prepareStatement(sql, new String[] {"id"});
			pstmt.setString(1, member.getEmail());
			pstmt.setString(2, member.getPassword());
			pstmt.setString(3, member.getName());
			pstmt.setTimestamp(4, Timestamp.valueOf(member.getRegisterDateTime()));
			return pstmt;
		}, keyHolder);
		Number keyValue = keyHolder.getKey();
		member.setId(keyValue.longValue());
		return keyValue.longValue();
	}
	
	public void update(Member member) {
		String sql = "update member set name = ?, password = ? where email = ?";
		jdbcTemplate.update(sql, member.getName(), member.getPassword(), member.getEmail());
	}
	
	public List<Member> selectByRegdate(LocalDateTime from, LocalDateTime to) {
		String sql = "select * from member where regdate between ? and ?" + "order by regdate desc";
		return jdbcTemplate.query(sql, new MemberMapper(), from, to);
	}
}

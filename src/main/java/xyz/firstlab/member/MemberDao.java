package xyz.firstlab.member;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

@Component
public class MemberDao {

    private static class MemberRowMapper implements RowMapper<Member> {
        @Override
        public Member mapRow(ResultSet rs, int rowNum) throws SQLException {
            Member member = new Member(rs.getString("EMAIL"), rs.getString("PASSWORD"), rs.getString("NAME"),
                                       rs.getTimestamp("REGDATE").toLocalDateTime());
            member.setId(rs.getLong("ID"));
            return member;
        }
    }

    private final JdbcTemplate jdbcTemplate;

    public MemberDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public Member selectByEmail(String email) {
        List<Member> results = jdbcTemplate.query("select * from MEMBER where EMAIL = ?", new MemberRowMapper(), email);
        return results.isEmpty() ? null : results.get(0);
    }

    public List<Member> selectAll() {
        return jdbcTemplate.query("select * from MEMBER", new MemberRowMapper());
    }

    public int count() {
        Integer count = jdbcTemplate.queryForObject("select count(*) from MEMBER", Integer.class);
        return count != null ? count : 0;
    }

    public void insert(Member member) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(con -> {
            PreparedStatement stmt =
                    con.prepareStatement("insert into MEMBER (EMAIL, PASSWORD, NAME, REGDATE) values (?, ?, ?, ?)",
                                         new String[]{"ID"});
            stmt.setString(1, member.getEmail());
            stmt.setString(2, member.getPassword());
            stmt.setString(3, member.getName());
            stmt.setTimestamp(4, Timestamp.valueOf(member.getRegisterDateTime()));
            return stmt;
        }, keyHolder);
        Number keyValue = keyHolder.getKey();
        member.setId(keyValue.longValue());
    }

    public void update(Member member) {
        jdbcTemplate.update("update MEMBER set NAME = ?, PASSWORD = ? where EMAIL = ?",
                            member.getName(), member.getPassword(), member.getEmail());
    }
}

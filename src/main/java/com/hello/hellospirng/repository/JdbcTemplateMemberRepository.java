package com.hello.hellospirng.repository;

import com.hello.hellospirng.domain.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class JdbcTemplateMemberRepository implements MemberRepository{

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcTemplateMemberRepository(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Member save(Member member) {

        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        //withTableName : 테이블명 , usingGeneratedKeyColumns는 키가 되는 컬럼 즉 MemberTable에서는 => id다
        jdbcInsert.withTableName("member").usingGeneratedKeyColumns("id"); //이렇게하면 쿼리를  짤 필요가없다

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("name", member.getName());

        Number key = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters));
        member.setId(key.longValue());
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        //JDBC Template를 이용한 조회, query를 작성해주고 결과가 나온것에 rowMapper라는걸로 매핑을해줘야한다
        List<Member> result = jdbcTemplate.query("select * from member where id = ?",memberRowMapper(),id);
        return result.stream().findAny();
    }

    @Override
    public Optional<Member> findByName(String name) {
        List<Member> result = jdbcTemplate.query("select * from member where name = ?", memberRowMapper(), name);
        return result.stream().findAny();
    }

    @Override
    public List<Member> findAll() {
        return jdbcTemplate.query("select * from member", memberRowMapper());

    }

    private RowMapper<Member> memberRowMapper() {
        return (rs, rowNum) -> {
           Member member = new Member();
           member.setId(rs.getLong("id"));
           member.setName(rs.getString("name"));
           return  member;
        };
    }
}

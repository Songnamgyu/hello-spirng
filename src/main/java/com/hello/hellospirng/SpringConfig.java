package com.hello.hellospirng;

import com.hello.hellospirng.repository.JdbcMemberRepository;
import com.hello.hellospirng.repository.MemberRepository;
import com.hello.hellospirng.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

//자바 코드로 직접 스프링빈 만들기
@Configuration
public class SpringConfig {

    private DataSource dataSource;


    public SpringConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
     //  return new MemoryMemberRepository();
         return new JdbcMemberRepository(dataSource);
    }
}

package com.hello.hellospirng;

import com.hello.hellospirng.repository.JpaMemberRepository;
import com.hello.hellospirng.repository.MemberRepository;
import com.hello.hellospirng.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;

//자바 코드로 직접 스프링빈 만들기
@Configuration
public class SpringConfig {

   // private DataSource dataSource;
    private EntityManager em;

    @Autowired
    public SpringConfig(EntityManager em) {
        this.em = em;
    }

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
     //  return new MemoryMemberRepository();
     //  return new JdbcMemberRepository(dataSource);
     //    return new JdbcTemplateMemberRepository(dataSource);
        return new JpaMemberRepository(em);
    }
}

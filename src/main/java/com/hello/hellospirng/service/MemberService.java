package com.hello.hellospirng.service;

import com.hello.hellospirng.domain.Member;
import com.hello.hellospirng.repository.MemberRepository;

import java.util.List;
import java.util.Optional;

public class MemberService {

    private final MemberRepository memberRepository ;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /**
    * 회원 가입
    * */
    public long join(Member member) {
        // 같은 이름이 있는 중복회원 X
        // ctrl + alt + v
        validateDuplicateMember(member); // 중복회원 검색
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        //Optional<Member> result = memberRepository.findByName(member.getName());
        //ifPresent 만약 값이 있으면 작동하는거 이게 Optional이 가능해서 작성가능한거다!
        //코드를 아래와 같이 정리가능하다
        memberRepository.findByName(member.getName())
                         .ifPresent(m -> {
                              throw new IllegalStateException("이미 존재하는 회원입니다");
                          });
    }

    /**
     * 전체 회원 조회
     * @return
     */
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }
}

package com.hello.hellospirng.controller;

import com.hello.hellospirng.domain.Member;
import com.hello.hellospirng.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class MemberController {

    //다른 여러 Controller들이 memberService를 가져다 쓸 수도 있기때문에 아래처럼 여러 인스턴스를 만들어서 사용하는거는 좋지않다.
    //private final MemberService memberService = new MemberService();

    // 그래서 SpringContainer에 등록해서 공용으로 쓰는게좋다
    private final MemberService memberService;

    //MemberContorller가 생성이 될때 SpringBean에 등록되어있는 MemberService객체를 넣어준다 -> Dependency Injection
    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/members/new")
    public String createForm() {
        return "members/createMemberForm";
    }
    @PostMapping("/members/new")
    public String create(MemberForm form) {
        Member member = new Member();
        member.setName(form.getName());
        System.out.println("member  이름 " + member.getName());
        memberService.join(member);

        return "redirect:/";
    }
    @GetMapping("/members")
    public String list(Model model) {
        List<Member> members = memberService.findMembers();
        model.addAttribute("members",members);

        return "/members/memberList";
    }
}

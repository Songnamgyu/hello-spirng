package com.hello.hellospirng.controller;

import com.hello.hellospirng.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

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
}

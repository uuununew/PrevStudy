package com.hanghae.study_board.controller;

import com.hanghae.study_board.dto.LoginRequest;
import com.hanghae.study_board.dto.RegisterRequest;
import com.hanghae.study_board.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MemberController {

    @Autowired
    MemberService memberService;

    @PostMapping("/login")
    public String login(@RequestBody LoginRequest loginRequest){
        boolean validated = memberService.validateMember(loginRequest);
        return validated ? "success" : "fail";
    }

    //회원가입
    @PostMapping("/register")
    public String register(@RequestBody RegisterRequest registerRequest){
        memberService.registerMember(registerRequest);
        return "Registed successfully";
    }
}

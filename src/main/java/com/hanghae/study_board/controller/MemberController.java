package com.hanghae.study_board.controller;

import com.hanghae.study_board.dto.LoginRequest;
import com.hanghae.study_board.dto.LoginResponse;
import com.hanghae.study_board.dto.RegisterRequest;
import com.hanghae.study_board.service.MemberService;
import jakarta.validation.Valid;
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
    public LoginResponse login(@RequestBody LoginRequest loginRequest){
        return memberService.validateMember(loginRequest);
    }

    //회원가입
    @PostMapping("/register")
    public String register(@RequestBody RegisterRequest registerRequest){
        memberService.registerMember(registerRequest);
        return "Registed successfully";
    }
}

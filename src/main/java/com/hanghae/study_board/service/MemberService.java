package com.hanghae.study_board.service;

import com.hanghae.study_board.dto.LoginRequest;
import com.hanghae.study_board.domain.Member;
import com.hanghae.study_board.dto.RegisterRequest;
import com.hanghae.study_board.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class MemberService {

    @Autowired MemberRepository memberRepository;

    @Autowired PasswordEncoder passwordEncoder; //PasswordEncoder 객체 생성

    public boolean validateMember(LoginRequest loginRequest){
        Member member = memberRepository.findByMemberId(loginRequest.getId())
                .orElseThrow(() -> new UsernameNotFoundException("User not fount with id: "+ loginRequest.getId()));
        return passwordEncoder.matches(loginRequest.getPw(), member.getPassword());
    }

    public void registerMember(RegisterRequest registerRequest){ //회원가입
        Member member = new Member();
        member.toEntity(registerRequest.getRegisterId(), passwordEncoder.encode(registerRequest.getRegisterPw()));
        memberRepository.save(member);
    }
}

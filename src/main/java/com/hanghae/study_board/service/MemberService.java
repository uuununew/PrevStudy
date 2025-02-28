package com.hanghae.study_board.service;

import com.hanghae.study_board.dto.LoginRequest;
import com.hanghae.study_board.domain.Member;
import com.hanghae.study_board.dto.LoginResponse;
import com.hanghae.study_board.dto.RegisterRequest;
import com.hanghae.study_board.repository.MemberRepository;
import com.hanghae.study_board.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor //Autowired 대체
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder; //PasswordEncoder 객체 생성
    private final JwtUtil jwtUtil;


    public LoginResponse validateMember(LoginRequest loginRequest){
        Member member = memberRepository.findByMemberId(loginRequest.getId())
                .orElseThrow(() -> new UsernameNotFoundException("User not fount with id: "+ loginRequest.getId()));
        if(passwordEncoder.matches(loginRequest.getPw(), member.getPassword())){
            String accessToken = jwtUtil.generateAccessToken(member.getMemberId());
            String refreshToken = jwtUtil.generateRefreshToken(member.getMemberId());
            return new LoginResponse(accessToken, refreshToken);
        }else{
            throw new RuntimeException("Invalid credentials");
        }

    }

    public void registerMember(RegisterRequest registerRequest){ //회원가입
        Member member = new Member();
        member.toEntity(registerRequest.getRegisterId(), passwordEncoder.encode(registerRequest.getRegisterPw()));
        memberRepository.save(member);
    }


}

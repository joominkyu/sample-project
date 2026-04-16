package com.example.backend.service;

import com.example.backend.entity.Member;
import com.example.backend.entity.MemberGrade;
import com.example.backend.repository.member.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public MemberResponse signup(MemberSignupRequest request) {
        if (memberRepository.existsByLoginId(request.getLoginId())) {
            throw new IllegalArgumentException("이미 사용 중인 아이디입니다.");
        }

        Member member = new Member(
                request.getName(),
                request.getLoginId(),
                request.getPassword(),
                MemberGrade.BASIC
        );

        Member saved = memberRepository.save(member);
        return MemberResponse.from(saved);
    }

    public MemberLoginResponse login(MemberLoginRequest request) {
        Member member = memberRepository.findByLoginId(request.getLoginId())
                .orElseThrow(() -> new IllegalArgumentException("아이디 또는 비밀번호가 올바르지 않습니다."));

        if (!member.getPassword().equals(request.getPassword())) {
            throw new IllegalArgumentException("아이디 또는 비밀번호가 올바르지 않습니다.");
        }

        return new MemberLoginResponse(
                "로그인 성공",
                member.getLoginId(),
                member.getName(),
                member.getGrade()
        );
    }

    public MemberResponse getMyPage(String loginId) {
        Member member = memberRepository.findByLoginId(loginId)
                .orElseThrow(() -> new IllegalArgumentException("회원 정보를 찾을 수 없습니다."));

        return MemberResponse.from(member);
    }
}

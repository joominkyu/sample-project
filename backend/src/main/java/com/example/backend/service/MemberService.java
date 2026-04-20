package com.example.backend.service;

import com.example.backend.entity.Member;
import com.example.backend.entity.MemberGrade;
import com.example.backend.repository.member.MemberLoginIdCheckResponse;
import com.example.backend.repository.member.MemberLoginRequest;
import com.example.backend.repository.member.MemberLoginResponse;
import com.example.backend.repository.member.MemberRepository;
import com.example.backend.repository.member.MemberResponse;
import com.example.backend.repository.member.MemberSignupRequest;
import lombok.RequiredArgsConstructor;
import org.example.util.XssUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    public MemberLoginIdCheckResponse checkLoginId(String loginId) {
        boolean exists = memberRepository.existsByLoginId(loginId);

        if (exists) {
            return new MemberLoginIdCheckResponse(false, "이미 사용 중인 아이디입니다.");
        }

        return new MemberLoginIdCheckResponse(true, "사용 가능한 아이디입니다.");
    }

    @Transactional
    public MemberResponse signup(MemberSignupRequest request) {
        if (memberRepository.existsByLoginId(request.getLoginId())) {
            throw new IllegalArgumentException("이미 사용 중인 아이디입니다.");
        }

        Member member = new Member(
                XssUtils.escape(request.getName()),
                XssUtils.escape(request.getLoginId()),
                passwordEncoder.encode(XssUtils.escape(request.getPassword())),
                MemberGrade.BASIC
        );

        Member saved = memberRepository.save(member);
        return MemberResponse.from(saved);
    }

    public MemberLoginResponse login(MemberLoginRequest request) {
        Member member = memberRepository.findByLoginId(request.getLoginId())
                .orElseThrow(() -> new IllegalArgumentException("아이디 또는 비밀번호가 올바르지 않습니다."));

        if (!passwordEncoder.matches(request.getPassword(), member.getPassword())) {
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
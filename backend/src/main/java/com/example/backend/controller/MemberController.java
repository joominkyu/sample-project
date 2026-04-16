package com.example.backend.controller;

import com.example.backend.repository.member.MemberLoginRequest;
import com.example.backend.repository.member.MemberLoginResponse;
import com.example.backend.repository.member.MemberResponse;
import com.example.backend.repository.member.MemberSignupRequest;
import com.example.backend.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/member")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/signup")
    public MemberResponse signup(@RequestBody MemberSignupRequest request) {
        return memberService.signup(request);
    }

    @PostMapping("/login")
    public MemberLoginResponse login(@RequestBody MemberLoginRequest request) {
        return memberService.login(request);
    }

    @GetMapping("/mypage")
    public MemberResponse myPage(@RequestParam String loginId) {
        return memberService.getMyPage(loginId);
    }
}

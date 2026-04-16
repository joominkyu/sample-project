package org.example.adminbackend.controller;

import lombok.RequiredArgsConstructor;
import org.example.adminbackend.repository.member.MemberGradeUpdateRequest;
import org.example.adminbackend.repository.member.MemberResponse;
import org.example.adminbackend.service.MemberService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/members")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping
    public List<MemberResponse> getMembers() {
        return memberService.getMembers();
    }

    @PatchMapping("/{memberId}/grade")
    public MemberResponse updateGrade(
            @PathVariable Long memberId,
            @RequestBody MemberGradeUpdateRequest request
    ) {
        return memberService.updateGrade(memberId, request);
    }
}
package org.example.adminbackend.service;

import lombok.RequiredArgsConstructor;
import org.example.adminbackend.entity.Member;
import org.example.adminbackend.entity.MemberGrade;
import org.example.adminbackend.repository.member.MemberRepository;
import org.example.adminbackend.repository.member.MemberResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public List<MemberResponse> getMembers() {
        return memberRepository.findAll()
                .stream()
                .map(MemberResponse::from)
                .toList();
    }

    public MemberResponse updateGrade(Long memberId, MemberGrade grade) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new RuntimeException("회원 없음"));

        member.changeGrade(grade);

        return MemberResponse.from(member);
    }
}
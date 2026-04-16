package org.example.adminbackend.service;

import lombok.RequiredArgsConstructor;
import org.example.adminbackend.entity.Member;
import org.example.adminbackend.repository.member.MemberGradeUpdateRequest;
import org.example.adminbackend.repository.member.MemberRepository;
import org.example.adminbackend.repository.member.MemberResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Transactional
    public MemberResponse updateGrade(Long memberId, MemberGradeUpdateRequest request) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new RuntimeException("회원이 존재하지 않습니다. id=" + memberId));

        member.changeGrade(request.getGrade());

        return MemberResponse.from(member);
    }
}
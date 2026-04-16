package com.example.backend.repository.member;

import com.example.backend.entity.Member;
import com.example.backend.entity.MemberGrade;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MemberResponse {
    private Long id;
    private String name;
    private String loginId;
    private MemberGrade grade;

    public static MemberResponse from(Member member) {
        return new MemberResponse(
                member.getId(),
                member.getName(),
                member.getLoginId(),
                member.getGrade()
        );
    }
}

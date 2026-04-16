package org.example.adminbackend.repository.member;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.example.adminbackend.entity.Member;
import org.example.adminbackend.entity.MemberGrade;

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
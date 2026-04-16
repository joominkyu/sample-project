package org.example.adminbackend.repository.member;

import lombok.Getter;
import lombok.Setter;
import org.example.adminbackend.entity.MemberGrade;

@Getter
@Setter
public class MemberGradeUpdateRequest {
    private MemberGrade grade;
}
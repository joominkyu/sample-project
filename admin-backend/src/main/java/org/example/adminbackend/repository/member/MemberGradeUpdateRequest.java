package org.example.adminbackend.repository.member;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.example.adminbackend.entity.MemberGrade;

@Getter
@Setter
public class MemberGradeUpdateRequest {

    @NotNull(message = "회원 등급을 선택해주세요.")
    private MemberGrade grade;
}
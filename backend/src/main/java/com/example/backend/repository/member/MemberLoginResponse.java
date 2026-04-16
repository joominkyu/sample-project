package com.example.backend.repository.member;

import com.example.backend.entity.MemberGrade;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MemberLoginResponse {
    private String message;
    private String loginId;
    private String name;
    private MemberGrade grade;
}

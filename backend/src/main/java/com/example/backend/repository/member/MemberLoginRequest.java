package com.example.backend.repository.member;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberLoginRequest {
    private String loginId;
    private String password;
}
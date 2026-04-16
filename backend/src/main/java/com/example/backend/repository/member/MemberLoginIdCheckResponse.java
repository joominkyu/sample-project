package com.example.backend.repository.member;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MemberLoginIdCheckResponse {
    private boolean available;
    private String message;
}
package org.example.adminbackend.repository.manager;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ManagerLoginResponse {
    private String message;
    private String loginId;
    private String name;
}
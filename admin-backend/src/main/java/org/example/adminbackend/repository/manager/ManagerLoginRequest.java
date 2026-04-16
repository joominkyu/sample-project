package org.example.adminbackend.repository.manager;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ManagerLoginRequest {
    private String loginId;
    private String password;
}
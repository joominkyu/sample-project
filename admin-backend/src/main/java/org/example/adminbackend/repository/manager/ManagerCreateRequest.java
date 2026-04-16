package org.example.adminbackend.repository.manager;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ManagerCreateRequest {
    private String loginId;
    private String password;
    private String name;
}
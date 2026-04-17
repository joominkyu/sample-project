package org.example.adminbackend.repository.manager;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ManagerPasswordChangeRequest {
    private String newPassword;
}
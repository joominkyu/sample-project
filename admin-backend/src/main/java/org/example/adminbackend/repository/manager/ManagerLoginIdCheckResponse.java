package org.example.adminbackend.repository.manager;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ManagerLoginIdCheckResponse {
    private boolean available;
    private String message;
}
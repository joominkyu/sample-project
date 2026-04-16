package org.example.adminbackend.repository.manager;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.example.adminbackend.entity.Manager;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class ManagerResponse {

    private Long id;
    private String loginId;
    private String name;
    private LocalDateTime createdAt;

    public static ManagerResponse from(Manager manager) {
        return new ManagerResponse(
                manager.getId(),
                manager.getLoginId(),
                manager.getName(),
                manager.getCreatedAt()
        );
    }
}
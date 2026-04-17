package org.example.adminbackend.repository.manager;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.example.adminbackend.entity.Manager;
import org.example.adminbackend.entity.ManagerGrade;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class ManagerResponse {

    private Long id;
    private String loginId;
    private String name;
    private ManagerGrade grade;
    private LocalDateTime createdAt;

    public static ManagerResponse from(Manager manager) {
        return new ManagerResponse(
                manager.getId(),
                manager.getLoginId(),
                manager.getName(),
                manager.getGrade(),
                manager.getCreatedAt()
        );
    }
}
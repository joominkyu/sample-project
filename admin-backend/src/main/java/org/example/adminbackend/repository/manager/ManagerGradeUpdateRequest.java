package org.example.adminbackend.repository.manager;

import lombok.Getter;
import lombok.Setter;
import org.example.adminbackend.entity.ManagerGrade;

@Getter
@Setter
public class ManagerGradeUpdateRequest {
    private ManagerGrade grade;
}
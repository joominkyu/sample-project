package org.example.adminbackend.repository.manager;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.example.adminbackend.entity.ManagerGrade;

@Getter
@Setter
public class ManagerGradeUpdateRequest {

    @NotNull(message = "관리자 등급을 선택해주세요.")
    private ManagerGrade grade;
}
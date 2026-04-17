package org.example.adminbackend.repository.manager;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ManagerPasswordChangeRequest {

    @NotBlank(message = "새 비밀번호를 입력해주세요.")
    @Size(min = 4, max = 100, message = "비밀번호는 4자 이상 입력해주세요.")
    private String newPassword;
}
package org.example.adminbackend.repository.board;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BoardCreateRequest {

    @NotBlank(message = "게시판명을 입력해주세요.")
    @Size(max = 100, message = "게시판명은 100자 이하로 입력해주세요.")
    private String name;
}
package org.example.adminbackend.repository.board;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BoardCreateRequest {

    @NotBlank(message = "게시판명을 입력해주세요.")
    private String name;

    private String content;
}
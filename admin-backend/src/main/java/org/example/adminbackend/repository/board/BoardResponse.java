package org.example.adminbackend.repository.board;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.example.adminbackend.entity.Board;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class BoardResponse {

    private Long id;
    private String name;
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public static BoardResponse from(Board board) {
        return new BoardResponse(
                board.getId(),
                board.getName(),
                board.getContent(),
                board.getCreatedAt(),
                board.getUpdatedAt()
        );
    }
}
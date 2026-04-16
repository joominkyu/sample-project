package org.example.adminbackend.controller;

import org.example.adminbackend.repository.board.BoardCreateRequest;
import org.example.adminbackend.repository.board.BoardResponse;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/admin/board")
public class BoardController {

    private final List<BoardResponse> boards = new ArrayList<>();

    public BoardController() {
        boards.add(new BoardResponse(1L, "공지사항"));
        boards.add(new BoardResponse(2L, "이벤트"));
        boards.add(new BoardResponse(3L, "FAQ"));
    }

    @GetMapping
    public List<BoardResponse> getBoardList() {
        return boards;
    }

    @PostMapping
    public BoardResponse createBoard(@RequestBody BoardCreateRequest request) {
        Long nextId = (long) (boards.size() + 1);
        BoardResponse newBoard = new BoardResponse(nextId, request.getName());
        boards.add(newBoard);
        return newBoard;
    }
}
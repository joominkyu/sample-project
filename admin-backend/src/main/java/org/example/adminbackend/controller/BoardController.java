package org.example.adminbackend.controller;

import lombok.RequiredArgsConstructor;
import org.example.adminbackend.repository.board.BoardCreateRequest;
import org.example.adminbackend.repository.board.BoardResponse;
import org.example.adminbackend.service.BoardService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/board")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    @GetMapping
    public List<BoardResponse> getBoardList() {
        return boardService.getBoardList();
    }

    @GetMapping("/{id}")
    public BoardResponse getBoard(@PathVariable Long id) {
        return boardService.getBoard(id);
    }

    @PostMapping
    public BoardResponse createBoard(@RequestBody BoardCreateRequest request) {
        return boardService.createBoard(request);
    }

    @PutMapping("/{id}")
    public BoardResponse updateBoard(
            @PathVariable Long id,
            @RequestBody BoardCreateRequest request
    ) {
        return boardService.updateBoard(id, request);
    }

    @DeleteMapping("/{id}")
    public void deleteBoard(@PathVariable Long id) {
        boardService.deleteBoard(id);
    }
}
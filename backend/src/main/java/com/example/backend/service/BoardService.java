package com.example.backend.service;

import com.example.backend.entity.Board;
import com.example.backend.repository.board.BoardRepository;
import com.example.backend.repository.board.BoardResponse;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BoardService {

    private final BoardRepository boardRepository;

    public List<BoardResponse> getBoardList() {
        return boardRepository.findAll()
                .stream()
                .map(BoardResponse::from)
                .toList();
    }

    public BoardResponse getBoard(Long id) {
        Board board = boardRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("게시판을 찾을 수 없습니다. id=" + id));

        return BoardResponse.from(board);
    }
}
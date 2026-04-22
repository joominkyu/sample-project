package org.example.adminbackend.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.example.adminbackend.entity.Board;
import org.example.adminbackend.repository.board.BoardCreateRequest;
import org.example.adminbackend.repository.board.BoardRepository;
import org.example.adminbackend.repository.board.BoardResponse;
import org.example.util.XssUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BoardService {

    private final BoardRepository boardRepository;

    public List<BoardResponse> getBoardList() {
        return boardRepository.findAllByOrderByCreatedAtDesc()
                .stream()
                .map(BoardResponse::from)
                .toList();
    }

    public BoardResponse getBoard(Long id) {
        Board board = boardRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("게시판을 찾을 수 없습니다. id=" + id));

        return BoardResponse.from(board);
    }

    @Transactional
    public BoardResponse createBoard(BoardCreateRequest request) {
        Board board = new Board(
                XssUtils.escape(request.getName()),
                request.getContent()
        );

        Board saved = boardRepository.save(board);
        return BoardResponse.from(saved);
    }

    @Transactional
    public BoardResponse updateBoard(Long id, BoardCreateRequest request) {
        Board board = boardRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("게시판을 찾을 수 없습니다. id=" + id));

        board.update(
                XssUtils.escape(request.getName()),
                request.getContent()
        );

        return BoardResponse.from(board);
    }

    @Transactional
    public void deleteBoard(Long id) {
        Board board = boardRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("게시판을 찾을 수 없습니다. id=" + id));

        boardRepository.delete(board);
    }
}
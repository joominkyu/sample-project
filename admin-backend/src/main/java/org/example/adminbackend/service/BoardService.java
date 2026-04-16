package org.example.adminbackend.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.example.adminbackend.entity.Board;
import org.example.adminbackend.repository.board.BoardCreateRequest;
import org.example.adminbackend.repository.board.BoardRepository;
import org.example.adminbackend.repository.board.BoardResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
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
                .orElseThrow(() ->
                        new EntityNotFoundException("게시판을 찾을 수 없습니다. id=" + id)
                );

        return BoardResponse.from(board);
    }

    public BoardResponse createBoard(BoardCreateRequest request) {
        Board board = new Board(request.getName());
        Board saved = boardRepository.save(board);
        return BoardResponse.from(saved);
    }

    @Transactional
    public BoardResponse updateBoard(Long id, BoardCreateRequest request) {
        Board board = boardRepository.findById(id)
                .orElseThrow(() ->
                        new EntityNotFoundException("게시판을 찾을 수 없습니다. id=" + id)
                );

        board.update(request.getName());
        return BoardResponse.from(board);
    }

    public void deleteBoard(Long id) {
        Board board = boardRepository.findById(id)
                .orElseThrow(() ->
                        new EntityNotFoundException("게시판을 찾을 수 없습니다. id=" + id)
                );

        boardRepository.delete(board);
    }
}
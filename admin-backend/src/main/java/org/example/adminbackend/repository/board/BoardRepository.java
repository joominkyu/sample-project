package org.example.adminbackend.repository.board;

import org.example.adminbackend.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board, Long> {
}
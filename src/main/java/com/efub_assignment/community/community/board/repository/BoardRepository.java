package com.efub_assignment.community.community.board.repository;

import com.efub_assignment.community.community.board.domain.Board;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BoardRepository extends JpaRepository<Board, Long> {
    Optional<Board> findById(Long id);

    List<Board> findByOrderByCreatedAtDesc();
}

package com.efub_assignment.community.community.board.repository;

import com.efub_assignment.community.community.board.domain.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface BoardRepository extends JpaRepository<Board, Long> {
    Optional<Board> findById(Long id);

    List<Board> findByOrderByCreatedAtDesc();

    //포스트 개수 증가
    @Modifying(clearAutomatically = true)
    @Query("Update Board b Set b.postCount = b.postCount + 1 Where b.id = :boardId")
    void increasePostCount(@Param("boardId") Long boardId);
}

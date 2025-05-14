package com.efub_assignment.community.community.comment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.efub_assignment.community.community.comment.domain.Comment;
import java.util.*;

public interface CommentRepository extends JpaRepository< Comment, Long> {
    List<Comment> findAllByPostIdOrderByCreatedAt(Long postId);  //만들어진 순으로 정렬
    List<Comment> findAllByWriterMemberIdOrderByCreatedAtDesc(Long memberId); //최신순 정렬
}

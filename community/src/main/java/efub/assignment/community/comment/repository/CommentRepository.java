package efub.assignment.community.comment.repository;

import efub.assignment.community.comment.domain.Comment;
import efub.assignment.community.post.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, Long>{
    List<Comment> findAllByPostIdOrderByCreatedAt(Long postId);
    List<Comment> findAllByWriterMemberIdOrderByCreatedAtDesc(Long memberId);

    Optional<Comment> findByCommentId(Long commentId);
}


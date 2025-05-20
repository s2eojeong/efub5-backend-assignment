package efub.assignment.community.comment.controller;

import efub.assignment.community.comment.dto.request.CommentUpdateRequest;
import efub.assignment.community.comment.service.CommentService;
import efub.assignment.community.post.dto.request.PostUpdateRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/comments")
public class CommentController {
    private final CommentService commentService;

    // 댓글 내용 수정
    @PatchMapping("/{id}")
    public ResponseEntity<Void> updatePostContent(@PathVariable("id") Long commentId,
                                                  @RequestHeader("Auth-Id") Long memberId,
                                                  @RequestHeader("Auth-Password") String password,
                                                  @Valid @RequestBody CommentUpdateRequest commentUpdateRequest){
        commentService.updateCommentContent(commentId, commentUpdateRequest, memberId, password);
        return ResponseEntity.noContent().build();
    }

    // 댓글 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteComment(@PathVariable("id") Long commentId,
                                              @RequestHeader("Auth-Id") Long memberId,
                                              @RequestHeader("Auth-Password") String password){
        commentService.deleteComment(commentId, memberId, password);
        return ResponseEntity.noContent().build();
    }
}

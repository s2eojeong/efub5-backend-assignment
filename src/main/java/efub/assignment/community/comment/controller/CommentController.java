package efub.assignment.community.comment.controller;

import efub.assignment.community.comment.domain.Comment;
import efub.assignment.community.comment.dto.request.CommentRequestDTO;
import efub.assignment.community.comment.dto.response.CommentListResponseDTO;
import efub.assignment.community.comment.dto.response.CommentResponseDTO;
import efub.assignment.community.comment.service.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class CommentController {
    private final CommentService commentService;
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    //댓글 생성
    @PostMapping("/posts/{postId}/comments")
    public ResponseEntity<CommentResponseDTO> createComment(@PathVariable Long postId, @RequestBody CommentRequestDTO requestDTO) {
        CommentResponseDTO created = commentService.create(postId, requestDTO);
        return (created != null)?
                ResponseEntity.status(HttpStatus.CREATED).body(created) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

    }

    //게시글에 해당하는 댓글 목록 조회
    @GetMapping("/posts/{postId}/comments")
    public ResponseEntity<CommentListResponseDTO> getCommentList(@PathVariable Long postId) {
        CommentListResponseDTO commentList = commentService.getCommentList(postId);
        //해당 게시글에 댓글이 없는 경우도 null임..(not ERROR)
        return ResponseEntity.status(HttpStatus.OK).body(commentList);
    }

    //작성자별 댓글 목록 조회
    @GetMapping("/members/{memberId}/comments")
    public ResponseEntity<CommentListResponseDTO> getMemberCommentList(@PathVariable Long memberId) {
        CommentListResponseDTO commentList = commentService.getMemberCommentList(memberId);
        return ResponseEntity.status(HttpStatus.OK).body(commentList);
    }

    //댓글 수정
    @PatchMapping("/posts/{postId}/comments/{commentId}")
    public ResponseEntity<CommentResponseDTO> updateComment(@PathVariable Long postId, @PathVariable Long commentId, @RequestBody CommentRequestDTO requestDTO) {
        CommentResponseDTO updatedComment = commentService.updateComment(postId, commentId, requestDTO);
        return (updatedComment != null)?
                ResponseEntity.status(HttpStatus.OK).body(updatedComment) :
                ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

    //댓글 삭제
    @DeleteMapping("/posts/{postId}/comments/{commentId}")
    public ResponseEntity<String> deleteComment(@PathVariable Long postId, @PathVariable Long commentId) {
        commentService.deleteComment(commentId);
        return ResponseEntity.status(HttpStatus.OK).body("댓글이 삭제되었습니다.");
    }

}

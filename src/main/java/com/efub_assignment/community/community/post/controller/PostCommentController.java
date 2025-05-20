package com.efub_assignment.community.community.post.controller;

import com.efub_assignment.community.community.comment.dto.request.CommentRequest;
import com.efub_assignment.community.community.comment.dto.request.CommentUpdateRequest;
import com.efub_assignment.community.community.comment.service.CommentService;
import com.efub_assignment.community.community.post.dto.response.PostCommentResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequiredArgsConstructor
@RequestMapping("/posts/{postId}/comments")
public class PostCommentController {
    private final CommentService commentService;

    @PostMapping
    public ResponseEntity<Void> createComment(@PathVariable("postId") Long postId,
                                              @RequestBody CommentRequest request){
        Long id = commentService.createComment(postId, request);
        return ResponseEntity.created(URI.create("/posts/"+postId+"/comments/"+id)).build();
    }

    @GetMapping
    public ResponseEntity<PostCommentResponse> getComment(@PathVariable("postId") Long postId){
        return ResponseEntity.ok(commentService.getPostCommentList(postId));
    }

    //댓글 수정
    @PatchMapping("/{commentId}")
    public ResponseEntity<String> updateComment(@PathVariable("commentId") Long commentId,
                                                @RequestBody CommentUpdateRequest request,
                                                @RequestHeader("Auth-Id") Long memberId,
                                                @RequestHeader("Auth-Password") String password){
        commentService.updateComment(commentId,request, memberId, password);
        return ResponseEntity.ok("댓글이 수정되었습니다.");
    }

    //댓글 삭제
    @DeleteMapping("/{commentId}")
    public ResponseEntity<String> deleteComment(@PathVariable("commentId") Long commentId,
                                                @RequestHeader("Auth-Id") Long memberId,
                                                @RequestHeader("Auth-Password") String password){
        commentService.deleteComment(commentId, memberId, password);
        return ResponseEntity.noContent().build();
    }
}

package com.efub_assignment.community.community.post.controller;

import com.efub_assignment.community.community.comment.dto.request.CommentRequest;
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
}

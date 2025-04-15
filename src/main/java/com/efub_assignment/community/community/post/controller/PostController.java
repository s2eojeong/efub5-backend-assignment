package com.efub_assignment.community.community.post.controller;

import com.efub_assignment.community.community.post.dto.request.PostCreateRequest;
import com.efub_assignment.community.community.post.dto.request.PostUpdateRequest;
import com.efub_assignment.community.community.post.dto.response.PostListResponse;
import com.efub_assignment.community.community.post.dto.response.PostResponse;
import com.efub_assignment.community.community.post.service.PostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.connector.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequiredArgsConstructor
@RequestMapping("/posts")
public class PostController {

    private final PostService postService;

    //게시물 생성
    @PostMapping
    public ResponseEntity<Void> createPost(@Valid @RequestBody PostCreateRequest request){
        Long postId = postService.createPost(request);
        return ResponseEntity.created(URI.create("/posts/"+postId)).build();
    }

    //게시물 목록 조회
    @GetMapping
    public ResponseEntity<PostListResponse> getAllPosts(){
        return ResponseEntity.ok(postService.getAllPosts());
    }

    //게시물 내용 조회
    @GetMapping("/{id}")
    public ResponseEntity<PostResponse> getPost(@PathVariable("id") Long postId){
        return ResponseEntity.ok(postService.getPost(postId));
    }

    //게시물 내용 수정
    @PatchMapping("/{id}")
    public ResponseEntity<Void> updatePostContent(@PathVariable("id") Long postId,
                                                  @RequestHeader("Auth-Id") Long memberId,
                                                  @RequestHeader("Auth-password") String password,
                                                  @Valid @RequestBody PostUpdateRequest postUpdateRequest){
        postService.updatePostContent(postId, postUpdateRequest, memberId, password);
        return ResponseEntity.noContent().build();
    }

    // 게시물 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePost(@PathVariable("id") Long postId,
                                           @RequestHeader("Auth-Id") Long memberId,
                                           @RequestHeader("Auth-password") String password){
        postService.deletePost(postId, memberId, password);
        return ResponseEntity.noContent().build();
    }

}

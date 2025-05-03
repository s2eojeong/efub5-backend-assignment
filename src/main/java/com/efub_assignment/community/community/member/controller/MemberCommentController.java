package com.efub_assignment.community.community.member.controller;

import com.efub_assignment.community.community.comment.service.CommentService;
import com.efub_assignment.community.community.member.dto.response.MemberCommentResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/members/{memberId}/comments")
public class MemberCommentController {
    private final CommentService commentService;

    @GetMapping
    public ResponseEntity<MemberCommentResponse> getMemberComments(@PathVariable("memberId") Long memberId){
        return ResponseEntity.ok(commentService.getMemberCommentList(memberId));
    }
}

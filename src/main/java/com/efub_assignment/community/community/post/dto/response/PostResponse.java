package com.efub_assignment.community.community.post.dto.response;

import com.efub_assignment.community.community.post.domain.Post;

import java.time.LocalDateTime;

public record PostResponse(Long postId, Long memberId, String nickname, String title, String content,
                           LocalDateTime createdAt , Long viewCount) {
    public static PostResponse from(Post post){
        return new PostResponse(
                post.getId(),
                post.getWriter().getMemberId(),
                post.getWriter().getNickname(),
                post.getTitle(),
                post.getContent(),
                post.getCreatedAt(),
                post.getViewCount()
        );

    }
}

package com.efub_assignment.community.community.comment.dto.response;

import com.efub_assignment.community.community.comment.domain.Comment;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class CommentResponse {
    private final Long commentId;
    private final Long postId;
    private final String writerNickname;
    private final String content;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;

    public static CommentResponse of(Comment comment){
        return CommentResponse.builder()
                .commentId(comment.getId())
                .postId(comment.getPost().getId())
                .writerNickname(comment.getWriter().getNickname())
                .content(comment.getContent())
                .createdAt(comment.getCreatedAt())
                .updatedAt(comment.getModifiedAt())
                .build();
    }
}

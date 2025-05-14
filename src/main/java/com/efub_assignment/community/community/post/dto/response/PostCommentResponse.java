package com.efub_assignment.community.community.post.dto.response;

import com.efub_assignment.community.community.comment.domain.Comment;
import com.efub_assignment.community.community.comment.dto.response.CommentResponse;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class PostCommentResponse {
    private final Long postId;
    private final List<CommentResponse> postCommentList;
    private final Long count;

    public static PostCommentResponse of(Long postId, List<Comment> commentList){
        return PostCommentResponse.builder()
                .postId(postId)
                .postCommentList(commentList.stream().map(CommentResponse::of).collect(Collectors.toList()))
                .count((long) commentList.size())
                .build();
    }
}

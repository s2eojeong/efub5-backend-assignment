package com.efub_assignment.community.community.comment.dto.request;

import com.efub_assignment.community.community.comment.domain.Comment;
import com.efub_assignment.community.community.member.domain.Member;
import com.efub_assignment.community.community.post.domain.Post;
import lombok.*;

@Getter
@Builder
@NoArgsConstructor(access= AccessLevel.PROTECTED)
@AllArgsConstructor(access=AccessLevel.PRIVATE)
public class CommentRequest {
    private Long memberId;
    private String content;

    public Comment toEntity(Member member, Post post){
        return Comment.builder()
                .content(content)
                .writer(member)
                .post(post)
                .build();
    }
}

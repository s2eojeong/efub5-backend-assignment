package efub.assignment.community.comment.dto.request;

import efub.assignment.community.comment.domain.Comment;
import efub.assignment.community.member.domain.Member;
import efub.assignment.community.post.domain.Post;
import lombok.*;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class CommentCreateRequest {

    private Long memberId;
    private String content;

    public Comment toEntity(Member member, Post post, boolean anonymous){
        return Comment.builder()
                .content(content)
                .writer(member)
                .post(post)
                .anonymous(anonymous)
                .build();
    }
}

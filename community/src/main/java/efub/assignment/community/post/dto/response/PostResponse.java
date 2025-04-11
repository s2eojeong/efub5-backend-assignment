package efub.assignment.community.post.dto.response;

import efub.assignment.community.post.domain.Post;
import java.time.LocalDateTime;

public record PostResponse(Long postId, Long accountId, String nickName, String title, String content,
                           LocalDateTime createdAt, Long viewCount) {
    public static PostResponse from(Post post) {
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

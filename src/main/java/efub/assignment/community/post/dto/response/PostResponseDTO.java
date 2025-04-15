package efub.assignment.community.post.dto.response;

import efub.assignment.community.post.domain.Post;

public class PostResponseDTO {
    private Long postId;
    private Long writerId;
    private boolean anonymity;
    private String content;

    public PostResponseDTO(Long postId, Long writerId, boolean anonymity, String content){
        this.postId = postId;
        this.writerId = writerId;
        this.anonymity = anonymity;
        this.content = content;
    }
    // Getter
    public Long getPostId() {
        return postId;
    }

    public Long getWriterId() {
        return writerId;
    }

    public boolean getAnonymity() {
        return anonymity;
    }

    public String getContent() {
        return content;
    }

    //정적 팩토리 메서드
    public static PostResponseDTO from (Post post){
        // Anonymity가 'true'일 경우 'writerId = null'
        Long writerId = post.isAnonymity() ? null : post.getWriter().getMemberId();

        return new PostResponseDTO(
              post.getPostId(),
              writerId,
              post.isAnonymity(),
              post.getContent()
      );
    }
}

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

    public static PostResponseDTO from (Post post){
      return new PostResponseDTO(
              post.getPostId(),
              post.getWriter().getMemberId(),
              post.isAnonymity(),
              post.getContent()
      );
    }
}

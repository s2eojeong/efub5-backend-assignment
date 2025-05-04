package efub.assignment.community.comment.dto.request;

import efub.assignment.community.comment.domain.Comment;
import efub.assignment.community.post.domain.Post;

public class CommentRequestDTO {
    private boolean anonymity;
    private String content;
    private Long postId;
    private Long commentorId;

    public CommentRequestDTO() {};
    public CommentRequestDTO(boolean anonymity, String content, Long postId, Long commentorId) {
        this.anonymity = anonymity;
        this.content = content;
        this.postId = postId;
        this.commentorId = commentorId;
    }

    //Getter
    public boolean isAnonymity() {
        return anonymity;
    }

    public String getContent() {
        return content;
    }

    public Long getPostId() {
        return postId;
    }

    public Long getCommentorId() {
        return commentorId;
    }

    //Setter
    public void setAnonymity(boolean anonymity) {
        this.anonymity = anonymity;
    }

    public void setContent(String content) {
        this.content = content;
    }

}

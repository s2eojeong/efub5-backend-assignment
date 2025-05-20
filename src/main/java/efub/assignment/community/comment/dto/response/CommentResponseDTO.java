package efub.assignment.community.comment.dto.response;

import efub.assignment.community.comment.domain.Comment;

public class CommentResponseDTO {
    private Long commentId;
    private String content;
    private boolean anonymity;
    private Long commentorId;

    public CommentResponseDTO(Long commentId, String content, boolean anonymity, Long commentorId) {
        this.commentId = commentId;
        this.content = content;
        this.anonymity = anonymity;
        this.commentorId = commentorId;
    }

    //Getter
    public Long getCommentId() {
        return commentId;
    }

    public String getContent() {
        return content;
    }

    public boolean isAnonymity() {
        return anonymity;
    }

    public Long getCommentorId() {
        return commentorId;
    }

    //정적 팩토리 메서드
    public static CommentResponseDTO from (Comment comment) {
        return new CommentResponseDTO(
                comment.getCommentId(),
                comment.getContent(),
                comment.isAnonymity(),
                comment.getCommentor().getMemberId()
        );
    }
}

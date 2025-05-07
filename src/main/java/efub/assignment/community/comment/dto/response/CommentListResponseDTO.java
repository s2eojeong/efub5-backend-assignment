package efub.assignment.community.comment.dto.response;

import efub.assignment.community.comment.domain.Comment;

import java.util.List;

public class CommentListResponseDTO {
    private List<CommentResponseDTO> commentList;
    public CommentListResponseDTO(List<CommentResponseDTO> commentList) { this.commentList = commentList; }

    public List<CommentResponseDTO> getCommentList() {
        return commentList;
    }

    public static CommentListResponseDTO from(List<Comment> commentList) {
        List<CommentResponseDTO> commentListDTO = commentList.stream()
                .map(CommentResponseDTO::from)
                .toList();
        return new CommentListResponseDTO(commentListDTO);
    }
}

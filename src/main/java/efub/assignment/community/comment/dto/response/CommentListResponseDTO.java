package efub.assignment.community.comment.dto.response;

import efub.assignment.community.comment.domain.Comment;

import java.util.Collections;
import java.util.List;

public class CommentListResponseDTO {
    private List<CommentResponseDTO> commentList;
    public CommentListResponseDTO(List<CommentResponseDTO> commentList) { this.commentList = commentList; }

    public List<CommentResponseDTO> getCommentList() {
        return commentList;
    }

    public static CommentListResponseDTO from(List<Comment> commentList) {
        //commentList가 비었을 경우 빈 배열 반환
        if (commentList == null || commentList.isEmpty()) {
            return new CommentListResponseDTO(Collections.emptyList());
        }

        List<CommentResponseDTO> commentListDTO = commentList.stream()
                .map(CommentResponseDTO::from)
                .toList();
        return new CommentListResponseDTO(commentListDTO);
    }
}

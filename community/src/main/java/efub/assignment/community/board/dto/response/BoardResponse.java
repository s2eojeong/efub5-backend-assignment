package efub.assignment.community.board.dto.response;

import efub.assignment.community.board.domain.Board;
import java.time.LocalDateTime;

public record BoardResponse(Long boardId, String title, String explanation, String notification, Long memberId,
                            LocalDateTime createdAt, LocalDateTime modifiedAt) {
    public static BoardResponse from(Board board){
        return new BoardResponse(
                board.getId(),
                board.getTitle(),
                board.getExplanation(),
                board.getNotification(),
                board.getOwner().getMemberId(),
                board.getCreatedAt(),
                board.getModifiedAt()
        );
    }
}

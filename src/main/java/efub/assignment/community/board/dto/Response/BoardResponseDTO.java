package efub.assignment.community.board.dto.Response;

import efub.assignment.community.board.domain.Board;

//게시판 생성 시 정보 반환 DTO
public class BoardResponseDTO {
    private Long boardId;
    private String boardName;
    private String announcement;
    private String description;
    private Long masterId;

    public BoardResponseDTO(Long boardId, String boardName, String announcement, String description, Long masterId){
        this.boardId = boardId;
        this.boardName = boardName;
        this.announcement = announcement;
        this.description = description;
        this.masterId = masterId;
    }

    // Getter
    public Long getBoardId() {
        return boardId;
    }

    public String getBoardName() {
        return boardName;
    }

    public String getAnnouncement() {
        return announcement;
    }

    public String getDescription() {
        return description;
    }

    public Long getMasterId() {
        return masterId;
    }

    // DTO 변환
    public static BoardResponseDTO from(Board board){
        return new BoardResponseDTO(
                board.getBoardId(),
                board.getBoardName(),
                board.getAnnouncement(),
                board.getDescription(),
                board.getMaster().getMemberId()
        );
    }

}

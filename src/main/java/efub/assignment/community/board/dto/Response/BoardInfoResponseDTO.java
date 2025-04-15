package efub.assignment.community.board.dto.Response;

import efub.assignment.community.board.domain.Board;

// 단일 게시판 조회 시 정보 반환 DTO
public class BoardInfoResponseDTO {
    private Long boardId;
    private String boardName;
    private String announcement;
    private String description;
    private Long masterId;

    public BoardInfoResponseDTO(Long boardId, String boardName, String announcement, String description, Long masterId){
        this.boardId = boardId;
        this.boardName = boardName;
        this.announcement = announcement;
        this.description = description;
        this.masterId = masterId;
    }

    //Getter
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

    public static BoardInfoResponseDTO from (Board board){
        return new BoardInfoResponseDTO(
                board.getBoardId(),
                board.getBoardName(),
                board.getAnnouncement(),
                board.getDescription(),
                board.getMaster().getMemberId()
        );
    }
}

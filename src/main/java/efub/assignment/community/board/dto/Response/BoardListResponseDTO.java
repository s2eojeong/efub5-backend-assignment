package efub.assignment.community.board.dto.Response;

import efub.assignment.community.board.domain.Board;

import java.util.List;

public class BoardListResponseDTO {
    private List<BoardResponseDTO> boards;

    public BoardListResponseDTO(List<BoardResponseDTO> boards){
        this.boards = boards;
    }

    public List<BoardResponseDTO> getBoards(){
        return boards;
    }

    public static BoardListResponseDTO from(List<Board> boardList){
        List<BoardResponseDTO> listDTO = boardList.stream()
                .map(BoardResponseDTO::from)
                .toList();
        return new BoardListResponseDTO(listDTO);
    }


}

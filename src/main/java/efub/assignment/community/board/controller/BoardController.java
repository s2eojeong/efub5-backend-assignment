package efub.assignment.community.board.controller;

import efub.assignment.community.board.dto.Request.BoardMasterRequestDTO;
import efub.assignment.community.board.dto.Response.BoardInfoResponseDTO;
import efub.assignment.community.board.dto.Response.BoardResponseDTO;
import efub.assignment.community.board.dto.Request.BoardRequestDTO;
import efub.assignment.community.board.dto.Response.BoardListResponseDTO;
import efub.assignment.community.board.service.BoardService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/boards")
public class BoardController {

    private final BoardService boardService;

    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    // 게시판 생성
    @PostMapping("/")
    public ResponseEntity<BoardResponseDTO> registerBoard(@RequestBody BoardRequestDTO requestDTO){
        BoardResponseDTO responseDTO = boardService.createBoard(requestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }

    // 게시판 목록 조회
    @GetMapping("/")
    public ResponseEntity<BoardListResponseDTO> getBoardList(){
        BoardListResponseDTO responseDTO = boardService.getBoardList();
        return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
    }

    // 단일 게시판 조회
    @GetMapping("/{boardId}")
    public ResponseEntity<BoardInfoResponseDTO> getBoardInfo(@PathVariable Long boardId){
        BoardInfoResponseDTO responseDTO = boardService.getBoardInfo(boardId);
        return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
    }

    // 게시판 관리자 변경
    @PatchMapping("/{boardId}")
    public ResponseEntity<BoardInfoResponseDTO> changeBoardMaster(@PathVariable Long boardId, @RequestBody BoardMasterRequestDTO requestDTO){
        BoardInfoResponseDTO responseDTO = boardService.changeBoardMaster(boardId, requestDTO);
        return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
    }

    // 게시판 삭제
    @DeleteMapping("/{boardId}")
    public ResponseEntity<String> deleteBoard(@PathVariable Long boardId){
        boardService.deleteBoard(boardId);
        return ResponseEntity.status(HttpStatus.OK).body("게시판이 삭제되었습니다.");
    }


}

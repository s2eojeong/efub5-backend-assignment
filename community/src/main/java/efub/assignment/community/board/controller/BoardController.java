package efub.assignment.community.board.controller;

import efub.assignment.community.board.dto.request.BoardCreateRequest;
import efub.assignment.community.board.dto.request.BoardUpdateRequest;
import efub.assignment.community.board.dto.response.BoardResponse;
import efub.assignment.community.board.service.BoardService;
import efub.assignment.community.post.dto.request.PostCreateRequest;
import efub.assignment.community.post.dto.request.PostUpdateRequest;
import efub.assignment.community.post.dto.response.PostListResponse;
import efub.assignment.community.post.service.PostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequiredArgsConstructor
@RequestMapping("/boards")
public class BoardController {
    private final BoardService boardService;

    // 게시판 생성
    @PostMapping
    public ResponseEntity<Void> createBoard(@RequestHeader("Auth-Id") Long ownerId,
                                            @Valid @RequestBody BoardCreateRequest request){
        Long boardId = boardService.createBoard(request, ownerId);
        return ResponseEntity.created(URI.create("/boards/" + boardId)).build();
    }

    // 게시판 조회
    @GetMapping("/{id}")
    public ResponseEntity<BoardResponse> getBoard(@PathVariable("id") Long id){
        return ResponseEntity.ok(boardService.getBoard(id));
    }

    // 게시판 주인 수정
    @PatchMapping("/{id}")
    public ResponseEntity<Void> updateOwner(@PathVariable("id") Long boardId,
                                                  @RequestHeader("Auth-Id") Long ownerId,
                                                  @RequestHeader("Auth-Password") String password,
                                                  @Valid @RequestBody BoardUpdateRequest request){
        boardService.updateBoard(boardId, request, ownerId, password);
        return ResponseEntity.noContent().build();
    }

    // 게시판 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBoard(@PathVariable("id") Long boardId,
                                           @RequestHeader("Auth-Id") Long ownerId,
                                           @RequestHeader("Auth-Password") String password){
        boardService.deleteBoard(boardId, ownerId, password);
        return ResponseEntity.noContent().build();
    }
}

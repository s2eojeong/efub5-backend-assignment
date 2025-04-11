package com.efub_assignment.community.community.board.controller;

import com.efub_assignment.community.community.board.dto.request.BoardCreateRequest;
import com.efub_assignment.community.community.board.dto.request.BoardUpdateRequest;
import com.efub_assignment.community.community.board.dto.response.BoardListResponse;
import com.efub_assignment.community.community.board.service.BoardService;
import com.efub_assignment.community.community.post.dto.request.PostUpdateRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@Controller
@RequiredArgsConstructor
@RequestMapping("/boards")
public class BoardController {

    private final BoardService boardService;

    //게시판 생성
    @PostMapping
    public ResponseEntity<Void> createBoard(@Valid @RequestBody BoardCreateRequest request){
        Long boardId = boardService.createBoard(request);
        return ResponseEntity.created(URI.create("/boards/"+boardId)).build();
    }

    //게시판 조회
    @GetMapping
    public ResponseEntity<BoardListResponse> getAllBoards(){
        return ResponseEntity.ok(boardService.getAllBoards());
    }

    //게시판 주인 수정
    @PatchMapping("/{id}")
    public ResponseEntity<Void> updateBoardOwner(@PathVariable("id") Long boardId,
                                                 @RequestHeader("Auth-Id") Long memberId,
                                                 @RequestHeader("Auth-password") String password,
                                                 @Valid @RequestBody BoardUpdateRequest boardUpdateRequest){
        boardService.updateBoardOwner(boardId, boardUpdateRequest, memberId, password);
        return ResponseEntity.noContent().build();
    }

    //게시판 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBoard(@PathVariable("id") Long boardId,
                                            @RequestHeader("Auth-Id") Long memberId,
                                            @RequestHeader("Auth-password") String password){
        boardService.deleteBoard(boardId, memberId, password);
        return ResponseEntity.noContent().build();
    }
}

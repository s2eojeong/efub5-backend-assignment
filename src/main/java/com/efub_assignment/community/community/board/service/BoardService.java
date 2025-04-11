package com.efub_assignment.community.community.board.service;

import com.efub_assignment.community.community.board.domain.Board;
import com.efub_assignment.community.community.board.dto.request.BoardCreateRequest;
import com.efub_assignment.community.community.board.dto.request.BoardUpdateRequest;
import com.efub_assignment.community.community.board.dto.response.BoardListResponse;
import com.efub_assignment.community.community.board.dto.response.BoardResponse;
import com.efub_assignment.community.community.board.repository.BoardRepository;
import com.efub_assignment.community.community.member.domain.Member;
import com.efub_assignment.community.community.member.repository.MemberRepository;
import com.efub_assignment.community.community.post.domain.Post;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;
    private final MemberRepository memberRepository;

    //게시판 생성
    @Transactional
    public Long createBoard(BoardCreateRequest boardCreateRequest){
        Long memberId = boardCreateRequest.memberId();
        Member owner = findByMemberId(memberId);
        Board newBoard = boardCreateRequest.toEntity(owner);
        boardRepository.save(newBoard);
        return newBoard.getId();
    }

    //게시판 조회
    @Transactional(readOnly = true)
    public BoardListResponse getAllBoards(){
        List<BoardResponse> boardResponse = boardRepository.findByOrderByCreatedAtDesc().stream()
                .map(BoardResponse::from).toList();
        return new BoardListResponse(boardResponse, boardRepository.count());
    }

    //게시판 주인 수정
    @Transactional
    public void updateBoardOwner(Long boardId, BoardUpdateRequest request, Long memberId, String password){
        Board board = findByBoardId(boardId);
        Member member = findByMemberId(memberId);
        authorizeBoardOwner(board, member, password);
        Member newOwner = findByMemberId(request.newOwnerId());
        board.changeOwner(newOwner);
    }

    @Transactional
    public void deleteBoard(Long boardId, Long memberId, String password){
        Board board = findByBoardId(boardId);
        Member member = findByMemberId(memberId);
        authorizeBoardOwner(board, member,password);
        boardRepository.delete(board);
    }

    private Board findByBoardId(Long boardId){
        return boardRepository.findById(boardId)
                .orElseThrow(()-> new NoSuchElementException("게시판을 찾을 수 없습니다."));
    }

    private Member findByMemberId(Long memberId){
        return memberRepository.findByMemberId(memberId)
                .orElseThrow(()-> new NoSuchElementException("회원을 찾을 수 없습니다."));
    }

    private void authorizeBoardOwner(Board board, Member member, String password){
        if(!board.getOwner().equals(member) || !board.getOwner().getPassword().equals(password)){
            throw new IllegalArgumentException("작성자가 아닙니다.");
        }
    }
}

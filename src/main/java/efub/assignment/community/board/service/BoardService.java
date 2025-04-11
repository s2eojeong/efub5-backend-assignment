package efub.assignment.community.board.service;

import efub.assignment.community.board.domain.Board;
import efub.assignment.community.board.dto.Request.BoardMasterRequestDTO;
import efub.assignment.community.board.dto.Response.BoardInfoResponseDTO;
import efub.assignment.community.board.dto.Response.BoardResponseDTO;
import efub.assignment.community.board.dto.Request.BoardRequestDTO;
import efub.assignment.community.board.dto.Response.BoardListResponseDTO;
import efub.assignment.community.board.repository.BoardRepository;
import efub.assignment.community.member.domain.Member;
import efub.assignment.community.member.repository.MemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BoardService {
    private final BoardRepository boardRepository;
    private final MemberRepository memberRepository;

    public BoardService(BoardRepository boardRepository, MemberRepository memberRepository) {
        this.boardRepository = boardRepository;
        this.memberRepository = memberRepository;
    }

    // 게시판 생성
    @Transactional
    public BoardResponseDTO createBoard(BoardRequestDTO requestDTO){
        Member master = memberRepository.findById(requestDTO.getMasterId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원입니다."));

        Board newBoard = Board.create(
                requestDTO.getBoardName(),
                requestDTO.getAnnouncement(),
                requestDTO.getDescription(),
                master
        );
        Board savedBoard = boardRepository.save(newBoard);
        return BoardResponseDTO.from(savedBoard);
    }

    // 게시판 목록 조회
    @Transactional(readOnly = true)
    public BoardListResponseDTO getBoardList(){
        List<Board> boardList = boardRepository.findAll();
        return BoardListResponseDTO.from(boardList);
    }

    // 게시판 정보 조회
    @Transactional(readOnly = true)
    public BoardInfoResponseDTO getBoardInfo(Long boardId){
        Board board = boardRepository.findById(boardId)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시판이 없습니다. id=" + boardId));;
        return BoardInfoResponseDTO.from(board);
    }

    // 게시판 관리자 변경
    @Transactional
    public BoardInfoResponseDTO changeBoardMaster(Long boardId, BoardMasterRequestDTO requestDTO){
        //1. 해당 게시판 찾기
        Board board = boardRepository.findById(boardId)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시판이 없습니다. id=" + boardId));
        //2. 해당 회원 존재하는지 확인
        Member newMaster = memberRepository.findById(requestDTO.getMasterId())
                .orElseThrow(() -> new IllegalArgumentException("해당 회원이 존재하지 않습니다."));
        //3. 관리자 변경
        board.setMaster(newMaster);

        return BoardInfoResponseDTO.from(board);
    }

    // 게시판 삭제
    @Transactional
    public void deleteBoard(Long boardId){
        Board deletedBoard = boardRepository.findById(boardId)
                        .orElseThrow(()-> new IllegalArgumentException("해당 게시판이 존재하지 않습니다."));
        boardRepository.deleteById(boardId);
    }

    // 게시물 등록

}

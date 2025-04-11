package efub.assignment.community.board.service;

import efub.assignment.community.board.domain.Board;
import efub.assignment.community.board.dto.request.BoardCreateRequest;
import efub.assignment.community.board.dto.request.BoardUpdateRequest;
import efub.assignment.community.board.dto.response.BoardResponse;
import efub.assignment.community.board.repository.BoardRepository;
import efub.assignment.community.global.exception.BlogException;
import efub.assignment.community.global.exception.ExceptionCode;
import efub.assignment.community.member.entity.Member;
import efub.assignment.community.member.repository.MembersRepository;
import efub.assignment.community.post.domain.Post;
import efub.assignment.community.post.dto.request.PostCreateRequest;
import efub.assignment.community.post.dto.request.PostUpdateRequest;
import efub.assignment.community.post.dto.response.PostResponse;
import efub.assignment.community.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardsRepository;
    private final MembersRepository membersRepository;


    @Transactional
    public Long createBoard(BoardCreateRequest request, Long ownerId) {
        Member owner = findByMemberId(ownerId);
        Board newboard = request.toEntity(owner);
        boardsRepository.save(newboard);
        return newboard.getId();
    }

    @Transactional(readOnly = true)
    public BoardResponse getBoard(Long boardId) {
        Board board = findByBoardId(boardId);
        return BoardResponse.from(board);
    }

    @Transactional
    public void updateBoard(Long boardId, BoardUpdateRequest request, Long ownerId, String password) {
        Board board = findByBoardId(boardId);
        Member member = findByMemberId(ownerId);
        authorizeOwner(board, member, password);

        Member newMem = findByMemberId(request.ownerId());
        board.changeOwner(newMem);
    }

    @Transactional
    public void deleteBoard(Long BoardId, Long ownerId, String password) {
        Board board = findByBoardId(BoardId);
        Member member = findByMemberId(ownerId);
        authorizeOwner(board, member, password);
        boardsRepository.delete(board);
    }

    private Member findByMemberId(Long id) {
        return membersRepository.findByMemberId(id)
                .orElseThrow(() -> new BlogException(ExceptionCode.MEMBER_NOT_FOUND));

    }

    private Board findByBoardId(Long boardId) {
        return boardsRepository.findById(boardId)
                .orElseThrow(()-> new BlogException(ExceptionCode.BOARD_NOT_FOUND));
    }

    private void authorizeOwner(Board board, Member member, String password) {
        if(!board.getOwner().equals(member)|| !board.getOwner().getPassword().equals(password)){
            throw new BlogException(ExceptionCode.BOARD_OWNER_MISMATCH);
        }
    }
}

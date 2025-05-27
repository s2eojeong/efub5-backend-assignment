package efub.assignment.community.comment.service;

import efub.assignment.community.board.service.BoardService;
import efub.assignment.community.comment.domain.Comment;
import efub.assignment.community.comment.dto.request.CommentRequestDTO;
import efub.assignment.community.comment.dto.request.CommentUpdateRequestDTO;
import efub.assignment.community.comment.dto.response.CommentListResponseDTO;
import efub.assignment.community.comment.dto.response.CommentResponseDTO;
import efub.assignment.community.comment.repository.CommentRepository;
import efub.assignment.community.member.domain.Member;
import efub.assignment.community.member.service.MemberService;
import efub.assignment.community.post.domain.Post;
import efub.assignment.community.post.service.PostService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CommentService {
    private final MemberService memberService;
    private final BoardService boardService;
    private final PostService postService;
    private final CommentRepository commentRepository;


    public CommentService(MemberService memberService, BoardService boardService, PostService postService, CommentRepository commentRepository) {
        this.memberService = memberService;
        this.boardService = boardService;
        this.postService = postService;
        this.commentRepository = commentRepository;
    }

    public CommentResponseDTO create(Long postId, CommentRequestDTO requestDTO) {
        //1. 유효성 검사
        Post post = postService.postValidation(postId);
        Member commentor = memberService.findMemberByMemberId(requestDTO.getCommentorId());

        //2. RequestDTO -> entity
        Comment newComment = Comment.create(
                post,
                requestDTO.isAnonymity(),
                requestDTO.getContent(),
                commentor
        );
        //3. 저장
        Comment savedComment = commentRepository.save(newComment);
        //4. entity -> ResponseDTO
        return CommentResponseDTO.from(savedComment);
    }

    @Transactional(readOnly = true)
    public CommentListResponseDTO getCommentList(Long postId) {
        //1. 유효성 검사
        Post post = postService.postValidation(postId);
        //2. post에 해당하는 CommentList 찾기
        List<Comment> commentList = commentRepository.findAllByPost(post);
        //3. DTO로 변환
        return CommentListResponseDTO.from(commentList);
    }

    @Transactional(readOnly = true)
    public CommentListResponseDTO getMemberCommentList(Long memberId) {
        //1. 유효성 검사
        Member member = boardService.memberValidation(memberId);
        //2. 해당 member이 작성한 CommentList 찾기
        List<Comment> commentList = commentRepository.findAllByCommentor(member);
        //3. DTO 변환
        return CommentListResponseDTO.from(commentList);
    }

    @Transactional
    public CommentResponseDTO updateComment(Long commentId, CommentUpdateRequestDTO requestDTO, Long memberId) {
        //1. commentId 유효성 검사
        Comment comment = commentValidation(commentId);
        //2. commmentor과 현재 로그인한 사용자 동일한지 확인
        Member member = memberService.findMemberByMemberId(memberId);
        if(!comment.getCommentor().equals(member)) {
            throw new IllegalArgumentException("해당 댓글이 존재하지 않습니다.");
        }
        //3. comment 수정
        comment.setContent(requestDTO.getContent());
        //4. DTO 반환
        return CommentResponseDTO.from(comment);
    }

    @Transactional
    public void deleteComment(Long commentId, Long memberId) {
        Comment comment = commentValidation(commentId);

        //해당 comment를 작성한 사용자가 현재 로그인된 사용자와 일치하는지도 체크 필요!!
        Member member = memberService.findMemberByMemberId(memberId);
        if(!comment.getCommentor().equals(member)) {
            throw new IllegalArgumentException("해당 댓글이 존재하지 않습니다.");
        }
        commentRepository.delete(comment);
    }
//++ 자바는 같은 메서드 스코프 안에서 동일한 이름으로 변수 두 번 선언 불가능 !!

    //CommentId 유효성 검사
    public Comment commentValidation(Long commentId) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new IllegalArgumentException("해당 댓글이 존재하지 않습니다."));
        return comment;
    }

}

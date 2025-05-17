package efub.assignment.community.comment.service;

import efub.assignment.community.board.service.BoardService;
import efub.assignment.community.comment.domain.Comment;
import efub.assignment.community.comment.dto.request.CommentRequestDTO;
import efub.assignment.community.comment.dto.response.CommentListResponseDTO;
import efub.assignment.community.comment.dto.response.CommentResponseDTO;
import efub.assignment.community.comment.repository.CommentRepository;
import efub.assignment.community.member.domain.Member;
import efub.assignment.community.member.repository.MemberRepository;
import efub.assignment.community.post.domain.Post;
import efub.assignment.community.post.repository.PostRepository;
import efub.assignment.community.post.service.PostService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CommentService {
    private final MemberRepository memberRepository;
    private final BoardService boardService;
    private final PostService postService;
    private final CommentRepository commentRepository;


    public CommentService(MemberRepository memberRepository, BoardService boardService, PostService postService, CommentRepository commentRepository) {
        this.memberRepository = memberRepository;
        this.boardService = boardService;
        this.postService = postService;
        this.commentRepository = commentRepository;
    }

    public CommentResponseDTO create(Long postId, CommentRequestDTO requestDTO) {
        //1. 유효성 검사
        Post post = postService.postValidation(postId);
        Member commentor = memberRepository.findById(requestDTO.getCommentorId()).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원입니다."));

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
    public CommentResponseDTO updateComment(Long commentId, CommentRequestDTO requestDTO) {
        //1. commentId 유효성 검사
        Comment comment = commentValidation(commentId);
        //2. comment 수정
        comment.setContent(requestDTO.getContent());
        //3. DTO 반환
        return CommentResponseDTO.from(comment);
    }

    @Transactional
    public void deleteComment(Long commentId) {
        Comment comment = commentValidation(commentId);
        commentRepository.delete(comment);
    }

    public Comment commentValidation(Long commentId) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new IllegalArgumentException("해당 댓글이 존재하지 않습니다."));
        return comment;
    }

}

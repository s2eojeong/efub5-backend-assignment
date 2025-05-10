package efub.assignment.community.comment.service;


import efub.assignment.community.comment.domain.Comment;
import efub.assignment.community.comment.dto.request.CommentRequest;
import efub.assignment.community.comment.dto.request.CommentUpdateRequest;
import efub.assignment.community.comment.repository.CommentRepository;
import efub.assignment.community.global.exception.BlogException;
import efub.assignment.community.global.exception.ExceptionCode;
import efub.assignment.community.member.dto.response.MemberCommentResponse;
import efub.assignment.community.member.entity.Member;
import efub.assignment.community.member.service.MembersService;
import efub.assignment.community.post.domain.Post;
import efub.assignment.community.post.dto.request.PostUpdateRequest;
import efub.assignment.community.post.dto.response.PostCommentResponse;
import efub.assignment.community.post.service.PostService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final MembersService membersService;
    private final PostService postService;
    private final CommentRepository commentRepository;

    @Transactional
    public Long createComment(Long postId, CommentRequest commentRequest){
        Long memberId = commentRequest.getMemberId();
        Member writer = membersService.findByMemberId(memberId);
        Post post = postService.findByPostId(postId);
        Comment newComment = commentRequest.toEntity(writer, post);
        commentRepository.save(newComment);
        return newComment.getId();
    }

    @Transactional(readOnly = true)
    public PostCommentResponse getPostCommentList(Long postId){
        List<Comment> commentList = commentRepository.findAllByPostIdOrderByCreatedAt(postId);
        return PostCommentResponse.of(postId, commentList);
    }

    @Transactional(readOnly = true)
    public MemberCommentResponse getMemberCommentList(Long memberId){
        Member member = membersService.findByMemberId(memberId);
        List<Comment> commentList = commentRepository.findAllByWriterMemberIdOrderByCreatedAtDesc(memberId);
        return MemberCommentResponse.of(member, commentList);
    }

    @Transactional
    public void updateCommentContent(Long commentId, CommentUpdateRequest request, Long memberId, String password) {
        Comment comment = findByCommentId(commentId);
        Member member = membersService.findByMemberId(memberId);
        authorizeCommentWriter(comment, member, password);
        comment.changeContent(request.content());
    }

    @Transactional(readOnly=true)
    private Comment findByCommentId(Long commentId) {
        return commentRepository.findById(commentId)
                .orElseThrow(() -> new BlogException(ExceptionCode.COMMENT_NOT_FOUND));
    }

    private void authorizeCommentWriter(Comment comment, Member member, String password) {
        if(!comment.getWriter().equals(member)|| !comment.getWriter().getPassword().equals(password)){
            throw new BlogException(ExceptionCode.COMMENT_ACCOUNT_MISMATCH);
        }
    }

}

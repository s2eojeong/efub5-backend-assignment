package com.efub_assignment.community.community.comment.service;


import com.efub_assignment.community.community.comment.domain.Comment;
import com.efub_assignment.community.community.comment.dto.request.CommentRequest;
import com.efub_assignment.community.community.comment.dto.request.CommentUpdateRequest;
import com.efub_assignment.community.community.comment.repository.CommentRepository;
import com.efub_assignment.community.community.member.dto.response.MemberCommentResponse;
import com.efub_assignment.community.community.member.service.MemberService;
import com.efub_assignment.community.community.member.domain.Member;
import com.efub_assignment.community.community.post.domain.Post;
import com.efub_assignment.community.community.post.dto.response.PostCommentResponse;
import com.efub_assignment.community.community.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final MemberService memberService;
    private final PostService postService;
    private final CommentRepository commentRepository;

    @Transactional
    public Long createComment(Long postId, CommentRequest commentRequest){
        Long memberId = commentRequest.getMemberId();
        Member writer = memberService.findByMemberId(memberId);
        Post post= postService.findByPostId(postId);
        Comment newComment = commentRequest.toEntity(writer, post);
        commentRepository.save(newComment);
        return newComment.getId(); //새로 저장된 댓글에 id 부여
    }

    @Transactional(readOnly = true)
    public PostCommentResponse getPostCommentList(Long postId){
        List<Comment> commentList = commentRepository.findAllByPostIdOrderByCreatedAt(postId);
        return PostCommentResponse.of(postId, commentList);
    }

    @Transactional(readOnly = true)
    public MemberCommentResponse getMemberCommentList(Long memberId){
        Member member = memberService.findByMemberId(memberId);
        List<Comment> commentList =commentRepository.findAllByWriterMemberIdOrderByCreatedAtDesc(memberId);
        return MemberCommentResponse.of(member, commentList);
    }
    //댓글 수정
    @Transactional
    public void updateComment(Long commentId, CommentUpdateRequest request){
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(()-> new IllegalArgumentException("댓글을 찾을 수 없습니다."));

        // 작성자 검증
        if (!comment.getWriter().getMemberId().equals(request.getMemberId())) {
            throw new IllegalArgumentException("댓글 작성자만 수정할 수 있습니다.");
        }

        comment.updateComment(request.getContent());
    }
}

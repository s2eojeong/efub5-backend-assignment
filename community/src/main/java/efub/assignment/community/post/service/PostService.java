package efub.assignment.community.post.service;

import efub.assignment.community.global.exception.BlogException;
import efub.assignment.community.global.exception.ExceptionCode;
import efub.assignment.community.member.entity.Member;
import efub.assignment.community.member.repository.MembersRepository;
import efub.assignment.community.post.domain.Post;
import efub.assignment.community.post.domain.PostLike;
import efub.assignment.community.post.dto.request.PostCreateRequest;
import efub.assignment.community.post.dto.request.PostUpdateRequest;
import efub.assignment.community.post.dto.response.PostListResponse;
import efub.assignment.community.post.dto.response.PostResponse;
import efub.assignment.community.post.dto.summary.PostSummary;
import efub.assignment.community.post.repository.PostLikeRepository;
import efub.assignment.community.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final MembersRepository MembersRepository;
    private final PostLikeRepository postLikeRepository;

    @Transactional
    public Long createPost(PostCreateRequest postCreateRequest) {
        Long memberId = postCreateRequest.memberId();
        Member writer = findByMemberId(memberId);
        Post newPost = postCreateRequest.toEntity(writer);
        postRepository.save(newPost);
        return newPost.getId();
    }

    @Transactional
    public PostResponse getPost(Long postId) {
        Post post = findByPostId(postId);
        return PostResponse.from(post);
    }

    @Transactional(readOnly = true)
    public PostListResponse getAllPosts() {
        List<PostSummary> postSummaries = postRepository.findByOrderByCreatedAtDesc().stream()
                .map(PostSummary::from).toList();
        return new PostListResponse(postSummaries, postRepository.count());
    }

    @Transactional
    public void updatePostContent(Long postId, PostUpdateRequest request, Long accountId, String password) {
        Post post = findByPostId(postId);
        Member member = findByMemberId(accountId);
        authorizePostWriter(post, member, password);
        post.changeContent(request.content());
    }

    @Transactional
    public void deletePost(Long postId, Long accountId, String password) {
        Post post = findByPostId(postId);
        Member member = findByMemberId(accountId);
        authorizePostWriter(post, member, password);
        postRepository.delete(post);
    }

    @Transactional(readOnly=true)
    public Post findByPostId(Long postId) {
        return postRepository.findById(postId)
                .orElseThrow(()-> new BlogException(ExceptionCode.POST_NOT_FOUND));
    }

    @Transactional(readOnly=true)
    private Member findByMemberId(Long accountId) {
        return MembersRepository.findByMemberId(accountId)
                .orElseThrow(() -> new BlogException(ExceptionCode.MEMBER_NOT_FOUND));

    }

    private void authorizePostWriter(Post post, Member member, String password) {
        if(!post.getWriter().equals(member)|| !post.getWriter().getPassword().equals(password)){
            throw new BlogException(ExceptionCode.POST_ACCOUNT_MISMATCH);
        }
    }

    // 게시글 좋아요
    public void likePost(Long postId, Long memberId) {
        Post post = findByPostId(postId);
        Member member = findByMemberId(memberId);

        //좋아요가 존재하는지 여부 확인
        if (postLikeRepository.existsByPostAndMember(post, member)){
            throw new BlogException(ExceptionCode.LIKE_ALREADY_EXISTS);
        }
        PostLike like = PostLike.builder()
                .post(post)
                .member(member)
                .build();
        postLikeRepository.save(like);
    }

    // 게시글 좋아요 취소
    public void unlikePost(Long postId, Long memberId) {
        Post post = findByPostId(postId);
        Member member = findByMemberId(memberId);
        PostLike like = postLikeRepository.findByPostAndMember(post, member)
                .orElseThrow(()->new BlogException(ExceptionCode.LIKE_NOT_FOUND));
        postLikeRepository.delete(like);
    }
}


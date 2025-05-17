package efub.assignment.community.post.service;

import efub.assignment.community.member.domain.Member;
import efub.assignment.community.board.domain.Board;
import efub.assignment.community.board.repository.BoardRepository;
import efub.assignment.community.member.repository.MemberRepository;
import efub.assignment.community.post.domain.Post;
import efub.assignment.community.post.domain.PostLike;
import efub.assignment.community.post.dto.request.PostRequestDTO;
import efub.assignment.community.post.dto.response.PostListResponseDTO;
import efub.assignment.community.post.dto.response.PostResponseDTO;
import efub.assignment.community.post.repository.PostLikeRepository;
import efub.assignment.community.post.repository.PostRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PostService {
    private final PostRepository postRepository;
    private final BoardRepository boardRepository;
    private final MemberRepository memberRepository;
    private final PostLikeRepository postLikeRepository;

    public PostService(PostRepository postRepository, BoardRepository boardRepository, MemberRepository memberRepository, PostLikeRepository postLikeRepository) {
        this.postRepository = postRepository;
        this.boardRepository = boardRepository;
        this.memberRepository = memberRepository;
        this.postLikeRepository = postLikeRepository;
    }

    //게시글 생성
    @Transactional
    public PostResponseDTO createPost(Long boardId, PostRequestDTO requestDTO){
        //1. 게시판 유효성 검사
        Board board = boardValidation(boardId);

        //2. 작성자 찾기
        Member writer = memberRepository.findById(requestDTO.getMemberId())
                .orElseThrow(() -> new IllegalArgumentException("작성자를 찾을 수 없습니다."));

        //3. Post 생성
        Post post = Post.create(
                board,
                writer,
                requestDTO.isAnonymity(),
                requestDTO.getContent()
        );

        //4. 저장
        Post savedPost = postRepository.save(post);

        //5. DTO로 변환
        return PostResponseDTO.from(savedPost);
    }

    //게시글 목록 조회
    @Transactional(readOnly = true)
    public PostListResponseDTO getPostList(Long boardId){
        //1. boardId 유효성 검사
        Board board = boardValidation(boardId);

        //2. 해당 board의 postList 찾기
        List<Post> posts = postRepository.findAllByBoard(board);

        //3. DTO 변환
        return PostListResponseDTO.from(posts);
    }

    //단일 게시글 정보 조회
    @Transactional(readOnly = true)
    public PostResponseDTO getPost(Long boardId, Long postId){
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 게시물입니다."));
        return PostResponseDTO.from(post);
    }

    //게시물 수정
    @Transactional
    public PostResponseDTO editPost(Long boardId, Long postId, PostRequestDTO requestDTO){
        //1. board 유효성 검사
        Board board = boardValidation(boardId);

        //2. post 유효성 검사
        Post post = postValidation(postId);

        //3. 게시물 수정
        post.setAnonymity(requestDTO.isAnonymity());
        post.setContent(requestDTO.getContent());

        //4. DTO 변환
        return PostResponseDTO.from(post);
    }

    // 게시물 삭제
    @Transactional
    public void deletePost(Long boardId, Long postId){
        //1. post 유효성 검사
        Post post = postValidation(postId);

        //2. 게시글의 좋아요 삭제
        postLikeRepository.deleteAllByPost(post);

        //3. 게시물 삭제
        postRepository.deleteById(postId);
    }

    //게시글 좋아요 등록
    @Transactional
    public void likePost(Long postId, Long memberId) {
        Post post = postValidation(postId);
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자를 찾을 수 없습니다."));
        //좋아요 여부 확인
        if(postLikeRepository.existsByPostAndMember(post, member)) {
            throw new IllegalArgumentException("이미 좋아요가 존재합니다.");
        }
        PostLike like = PostLike.builder()
                .post(post)
                .member(member)
                .build();
        postLikeRepository.save(like);
    }

    //게시글 좋아요 취소
    @Transactional
    public void unlikePost(Long postId, Long memberId) {
        Post post = postValidation(postId);
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자를 찾을 수 없습니다."));
        //좋아요 존재 여부 확인
        if (!postLikeRepository.existsByPostAndMember(post, member)) {
            throw new IllegalArgumentException("해당 좋아요는 존재하지 않습니다.");
        }

        PostLike like = postLikeRepository.findByPostAndMember(post, member)
                .orElseThrow(() -> new IllegalArgumentException("해당 좋아요을 찾을 수 없습니다."));
        postLikeRepository.delete(like);
    }

    // - 유효성 검사 -
    //boardId 유효성 검사
    public Board boardValidation(Long boardId){
        Board board = boardRepository.findById(boardId).orElseThrow(()->new IllegalArgumentException("게시판을 찾을 수 없습니다."));
        return board;
    }

    //postId 유효성 검사
    public Post postValidation(Long postId){
        Post post = postRepository.findById(postId).orElseThrow(() -> new IllegalArgumentException("게시물을 찾을 수 없습니다."));
        return post;
    }
}

package efub.assignment.community.post.service;

import efub.assignment.community.member.domain.Member;
import efub.assignment.community.board.domain.Board;
import efub.assignment.community.board.repository.BoardRepository;
import efub.assignment.community.member.repository.MemberRepository;
import efub.assignment.community.post.domain.Post;
import efub.assignment.community.post.dto.request.PostRequestDTO;
import efub.assignment.community.post.dto.response.PostListResponseDTO;
import efub.assignment.community.post.dto.response.PostResponseDTO;
import efub.assignment.community.post.repository.PostRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PostService {
    private final PostRepository postRepository;
    private final BoardRepository boardRepository;
    private final MemberRepository memberRepository;

    public PostService(PostRepository postRepository, BoardRepository boardRepository, MemberRepository memberRepository) {
        this.postRepository = postRepository;
        this.boardRepository = boardRepository;
        this.memberRepository = memberRepository;
    }

    //게시글 생성
    @Transactional
    public PostResponseDTO createPost(Long boardId, PostRequestDTO requestDTO){
        //1. 게시판 유효성 검사
        Board board = boardRepository.findById(boardId)
                .orElseThrow(() -> new IllegalArgumentException("게시판을 찾을 수 없습니다."));

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
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("게시글을 찾을 수 없습니다."));

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
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("게시물을 찾을 수 없습니다."));

        //2. 게시물 삭제
        postRepository.deleteById(postId);
    }

    //boardId 유효성 검사
    public Board boardValidation(Long boardId){
        Board board = boardRepository.findById(boardId).orElseThrow(()->new IllegalArgumentException("게시판을 찾을 수 없습니다."));
        return board;
    }
}

package efub.assignment.community.post.controller;

import efub.assignment.community.post.dto.request.PostRequestDTO;
import efub.assignment.community.post.dto.response.PostListResponseDTO;
import efub.assignment.community.post.dto.response.PostResponseDTO;
import efub.assignment.community.post.service.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/boards/{boardId}/posts")
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    // 게시글 생성
    //@PostMapping("/")     -- RESTful API에서는 URL 끝에 슬래시(/) 붙이지 않는 것이 관례임
    @PostMapping
    public ResponseEntity<PostResponseDTO> createPost(@PathVariable Long boardId, @RequestBody PostRequestDTO requestDTO){
        PostResponseDTO responseDTO = postService.createPost(boardId, requestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }

    // 게시글 목록 조회
    @GetMapping
    public ResponseEntity<PostListResponseDTO> getPostList(@PathVariable Long boardId) {
        PostListResponseDTO responseDTO = postService.getPostList(boardId);
        return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
    }

    // 단일 게시글 조회
    @GetMapping("/{postId}")
    public ResponseEntity<PostResponseDTO> getPost(@PathVariable Long boardId, @PathVariable Long postId){
        PostResponseDTO responseDTO = postService.getPost(boardId, postId);
        return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
    }

    // 게시글 수정
    @PatchMapping("/{postId}")
    public ResponseEntity<PostResponseDTO> editPost(@PathVariable Long boardId, @PathVariable Long postId, @RequestBody PostRequestDTO requestDTO){
        PostResponseDTO responseDTO = postService.editPost(boardId, postId, requestDTO);
        return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
    }

    // 게시글 삭제
    @DeleteMapping("/{postId}")
    public ResponseEntity<String> deletePost(@PathVariable Long boardId, @PathVariable Long postId){
        postService.deletePost(boardId, postId);
        return ResponseEntity.status(HttpStatus.OK).body("게시물이 삭제되었습니다.");
    }

    // 게시글 좋아요 등록
    @PostMapping("/{postId}/like")
    public ResponseEntity<String> likePost(@PathVariable Long postId,
                                           @RequestHeader("memberId") Long memberId) {
        postService.likePost(postId, memberId);
        return ResponseEntity.status(HttpStatus.OK).body("좋아요가 생성되었습니다.");
    }

    // 게시글 좋아요 취소
    @DeleteMapping("/{postId}/like")
    public ResponseEntity<String> unlikePost(@PathVariable Long postId,
                                             @RequestHeader("memberId") Long memberId) {
        postService.deletePost(postId, memberId);
        return ResponseEntity.status(HttpStatus.OK).body("좋아요가 취소되었습니다.");
    }
}

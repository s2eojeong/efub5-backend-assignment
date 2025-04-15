package efub.assignment.community.post.dto.response;

import efub.assignment.community.post.domain.Post;

import java.util.List;

public class PostListResponseDTO {
    private List<PostResponseDTO> posts;

    public PostListResponseDTO(List<PostResponseDTO> posts){
        this.posts = posts;
    }

    public List<PostResponseDTO> getPosts() {
        return posts;
    }

    public static PostListResponseDTO from(List<Post> postList){
        List<PostResponseDTO> postListDTO = postList.stream()
                .map(PostResponseDTO::from)
                .toList();
        return new PostListResponseDTO(postListDTO);
    }
}

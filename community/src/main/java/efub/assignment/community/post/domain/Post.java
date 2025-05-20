package efub.assignment.community.post.domain;

import efub.assignment.community.comment.domain.Comment;
import efub.assignment.community.global.domain.BaseEntity;
import efub.assignment.community.member.entity.Member;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Post extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 제목
    @Column(nullable = false)
    private String title;

    // 내용
    @Column(nullable = false)
    private  String content;

    // 글쓴이
    @ManyToOne(fetch = FetchType.LAZY)
    private Member writer;

    // 조회수
    private Long viewCount;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> commentList = new ArrayList<>();

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    List<PostLike> postLikeList = new ArrayList<>();

    // 빌더
    @Builder
    public Post(String title, String content, Member writer){
        this.title = title;
        this.content = content;
        this.writer = writer;
        this.viewCount = 0L;
    }

    //게시글 내용 수정
    public void changeContent(String newContent){
        this.content = newContent;
    }


}

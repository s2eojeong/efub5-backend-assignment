package efub.assignment.community.post.domain;

import efub.assignment.community.global.domain.BaseEntity;
import efub.assignment.community.member.entity.Member;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Post extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 제목
    private String title;

    // 내용
    private  String content;

    // 글쓴이
    @ManyToOne(fetch = FetchType.LAZY)
    private Member writer;

    // 조회수
    private Long viewCount;

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

package efub.assignment.community.board.domain;

import efub.assignment.community.global.domain.BaseEntity;
import efub.assignment.community.member.entity.Member;
import efub.assignment.community.post.domain.Post;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "boards")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Board extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 제목
    @Column(nullable = false)
    private String title;

    // 설명
    private  String explanation;

    // 공지사항
    private String notification;

    // owner
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id")
    private Member owner;



    // 빌더
    @Builder
    public Board(String title, String explanation, String notification, Member owner){
        this.title = title;
        this.explanation = explanation;
        this.notification = notification;
        this.owner = owner;
    }

    //게시판 주인 수정
    public void changeOwner(Member newOwner){
        this.owner = newOwner;
    }

}

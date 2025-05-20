package efub.assignment.community.post.domain;

import efub.assignment.community.global.domain.BaseEntity;
import efub.assignment.community.member.domain.Member;
import jakarta.persistence.*;
import lombok.*;

//JPA에서 권장되는 기본 생성자 선언은 불필요하게 외부에서 객체가 생성되는 것을 막기위해 protected 사용 - (default = public)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
//@AllArgsConstructor   -> builder가 명시적 생성자 이미 만들고 있음 - 필요 없음
@Getter
@Entity
public class PostLike extends BaseEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="post_like_id")
    private Long likeId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="post_id", updatable = false, nullable = false)
    private Post post;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", updatable = false, nullable = false)
    private Member member;

    @Builder
    public PostLike(Post post, Member member) {
        this.post = post;
        this.member = member;
    }

    /*
    Builder VS AllArgsConstructor
    - @Builder
      Builder 패턴을 사용해서 객체 생성할 수 있게 해줌
      가독성이 좋은 객체 생성을 위해 사용
      매개 변수가 많거나, 선택적 필드가 있을 경우 유리
      내부적으로 필드들을 받는 private 생성자 만들어 사용
      
    - @AllArgsConstructor
      모든 필드를 파라미터로 받는 전체 생성자 생성
      간단하게 객체를 빠르게 만들고 싶을 때 생성 
      명시적으로 생성자 주입시 필요


      ??? 근데 Builder 패턴이 뭐지 ???
      " 복잡한 객체를 단계별로 유연하게 생성할 수 있도록 해주는 패턴 "
      - 생성자 파라미터가 많음  - 어떤 필드는 필수, 어떤건 선택  - 필드 순서 헷갈리기 쉬움  - 중복된 생성자 만들기 싫을 때
     */
}

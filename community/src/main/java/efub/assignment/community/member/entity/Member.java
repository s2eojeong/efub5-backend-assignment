package efub.assignment.community.member.entity;

import efub.assignment.community.comment.domain.Comment;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Table(name = "members")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberId;

    // 이메일
    @Column(nullable = false)
    private String email;

    // 비밀번호
    @Column(nullable = false)
    private String password;

    // 닉네임(수정가능)
    @Column(nullable = false)
    private String nickname;

    // 대학이름
    @Column(nullable = false, updatable = false)
    private String university;

    // 학번, 중복 안됨
    @Column(unique = true)
    private String studentId;

    // 회원 상태
    @Enumerated
    @Column
    private MemberStatus status = MemberStatus.ACTIVE;

    @OneToMany(mappedBy = "writer", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> commentList = new ArrayList<>();

    @Builder
    public Member(String email, String password, String nickname, String university, String studentId) {
        this.email = email;
        this.password = password;
        this.nickname = nickname;
        this.studentId = studentId;
        this.university = university;
    }

    public void updateNickname(String nickname) {
        this.nickname = nickname;
    }

    public void changeStatus(MemberStatus status) {
        this.status = status;
    }
}

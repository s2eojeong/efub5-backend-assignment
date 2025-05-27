package efub.assignment.community.alarm;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Alarm {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 알림을 받을 사람
    @Column(nullable = false)
    private Long memberId;

    // 쪽지방 or 댓글 알림
    @Column(nullable = false)
    private String type;

    @Column(nullable = false)
    private String content; // 알림 내용

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @Builder
    public Alarm(Long memberId, String type, String content) {
        this.memberId = memberId;
        this.type = type;
        this.content = content;
        this.createdAt = LocalDateTime.now();
    }
}


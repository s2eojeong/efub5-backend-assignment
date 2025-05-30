package efub.assignment.community.notification.domain;

import efub.assignment.community.global.domain.BaseEntity;
import efub.assignment.community.member.domain.Member;
import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
@Table(name = "notifications")
public class Notification extends BaseEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long notiId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "receiver_id", nullable = false)
    private Member receiver;

    @Column
    private String message;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private NotificationType type;

    @Column(nullable = false)
    private boolean isRead = false;

    //알림 종류별 추가 데이터 (댓글 알림일 경우)
    private String boardName;
    private String commentContent;

    public static Notification create(Member receiver, NotificationType type, String message, String boardName, String commentContent) {
        Notification noti = new Notification();
        noti.receiver = receiver;
        noti.type = type;
        noti.message = message;
        noti.boardName = boardName;
        noti.commentContent = commentContent;
        noti.isRead = false;
        return noti;
    }

    public void markAsRead() {
        this.isRead = true;
    }
}

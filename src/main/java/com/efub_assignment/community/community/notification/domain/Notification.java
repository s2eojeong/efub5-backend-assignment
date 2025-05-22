package com.efub_assignment.community.community.notification.domain;

import com.efub_assignment.community.community.global.entity.BaseTimeEntity;
import com.efub_assignment.community.community.member.domain.Member;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Notification extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long notionId;

    @Enumerated(EnumType.STRING)
    private NotificationType type;

    private String content;

    private String boardName; //댓글 알람에 사용

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "receiver_id", nullable = false)
    private Member receiver;

    @Builder
    public Notification(NotificationType type, String content, String boardName, Member receiver){
        this.type = type;
        this.content = content;
        this.boardName = boardName;
        this.receiver = receiver;
    }

}

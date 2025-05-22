package com.efub_assignment.community.community.notification.dto.response;

import com.efub_assignment.community.community.notification.domain.Notification;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.Locale;

@Builder
@Getter
public class NotificationResponse {
    private String content;
    private String type;
    private String boardName; //댓글 알림일때만 포함
    private LocalDateTime createdAt;

    public static NotificationResponse from(Notification notification){
        return NotificationResponse.builder()
                .type(notification.getType().name().toLowerCase())
                .content(notification.getContent())
                .boardName(notification.getBoardName())
                .createdAt(notification.getCreatedAt())
                .build();
    }

}

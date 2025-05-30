package efub.assignment.community.notification.dto.response;

import efub.assignment.community.notification.domain.Notification;
import efub.assignment.community.notification.domain.NotificationType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@AllArgsConstructor
@Getter
@Builder
public class NotificationListResponseDTO {
    private Long id;
    private NotificationType type;                // ex: "댓글", "쪽지방"
    private String message;
    private Map<String, Object> data;   // 추가 정보 (null 가능)
    private LocalDateTime createdAt;
    private boolean isRead;

    public static NotificationListResponseDTO from(Notification notification) {
        Map<String, Object> extraData = null;

        // 상황별 추가 데이터 구성
        if (notification.getType() == NotificationType.COMMENT) {
            extraData = new HashMap<>();
            extraData.put("board_name", notification.getBoardName());
            extraData.put("commentContent", notification.getCommentContent());
        }

        return NotificationListResponseDTO.builder()
                .id(notification.getNotiId())
                .type(notification.getType())
                .message(notification.getMessage())
                .data(extraData)
                .createdAt(notification.getCreatedAt())
                .isRead(notification.isRead())
                .build();
    }
}

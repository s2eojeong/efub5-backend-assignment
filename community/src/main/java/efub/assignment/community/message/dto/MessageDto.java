package efub.assignment.community.message.dto;

import lombok.*;
import java.time.LocalDateTime;


@Builder
@Getter
@AllArgsConstructor
public class MessageDto {
    private String content;
    private LocalDateTime createdAt;
    private boolean isSent; // true이면 내가 보낸 메시지
}

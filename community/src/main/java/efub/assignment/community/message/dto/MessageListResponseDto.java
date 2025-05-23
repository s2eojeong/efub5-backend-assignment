package efub.assignment.community.message.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@Getter
@AllArgsConstructor
public class MessageListResponseDto {
    private Long messageRoomId;
    private Long partnerId;
    private List<MessageDto> messages;
}

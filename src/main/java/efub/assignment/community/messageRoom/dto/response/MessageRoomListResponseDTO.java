package efub.assignment.community.messageRoom.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
public class MessageRoomListResponseDTO {
    private Long senderId;
    private Long receiverId;
    private Long msgRoomId;
    private String lastestMessageAt;
    private LocalDateTime sendAt;
}

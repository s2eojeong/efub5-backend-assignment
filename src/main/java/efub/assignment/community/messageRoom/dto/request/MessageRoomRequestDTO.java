package efub.assignment.community.messageRoom.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MessageRoomRequestDTO {
    private Long senderId;
    private Long receiverId;
    private Long postId;
}

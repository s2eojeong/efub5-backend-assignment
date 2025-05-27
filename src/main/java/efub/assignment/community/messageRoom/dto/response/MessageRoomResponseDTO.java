package efub.assignment.community.messageRoom.dto.response;

import efub.assignment.community.messageRoom.domain.MessageRoom;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class MessageRoomResponseDTO {
    private Long senderId;
    private Long receiverId;
    private Long messageRoomId;

    public static MessageRoomResponseDTO from(MessageRoom messageRoom) {
        return new MessageRoomResponseDTO(
                messageRoom.getSender().getMemberId(),
                messageRoom.getReceiver().getMemberId(),
                messageRoom.getMessageRoomId()
        );
    }
}

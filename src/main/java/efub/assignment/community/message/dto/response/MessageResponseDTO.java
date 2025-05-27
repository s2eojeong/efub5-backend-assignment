package efub.assignment.community.message.dto.response;

import efub.assignment.community.message.domain.Message;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class MessageResponseDTO {
    private Long messageRoomId;
    private Long senderId;
    private Long receiverId;
    private String text;
    private LocalDateTime createdDate;

    public static MessageResponseDTO from(Message message) {
        return new MessageResponseDTO(
                message.getMessageId(),
                message.getSender().getMemberId(),
                message.getReceiver().getMemberId(),
                message.getText(),
                message.getCreatedAt()
        );
    }
}

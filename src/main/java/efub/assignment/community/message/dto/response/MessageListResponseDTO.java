package efub.assignment.community.message.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class MessageListResponseDTO {
    private Long messageRoomId;
    private Long opponentId;
    private List<MessageResponseDTO> messages;
}

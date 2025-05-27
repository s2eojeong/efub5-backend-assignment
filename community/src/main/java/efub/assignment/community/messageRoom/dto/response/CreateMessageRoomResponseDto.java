package efub.assignment.community.messageRoom.dto.response;

import efub.assignment.community.messageRoom.domain.MessageRoom;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Builder
@Getter
@AllArgsConstructor
public class CreateMessageRoomResponseDto {
    private Long messageRoomId;
    private Long senderId;
    private Long receiverId;
    private String initialMessage;
    private Long postId;
    private LocalDateTime createdAt;

    public static CreateMessageRoomResponseDto from(MessageRoom room) {
        return CreateMessageRoomResponseDto.builder()
                .messageRoomId(room.getId())
                .senderId(room.getSenderId())
                .receiverId(room.getReceiverId())
                .initialMessage(room.getInitialMessage())
                .postId(room.getPostId())
                .createdAt(room.getCreatedAt())
                .build();
    }
}

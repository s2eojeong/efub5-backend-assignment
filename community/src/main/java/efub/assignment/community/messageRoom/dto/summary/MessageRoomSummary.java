package efub.assignment.community.messageRoom.dto.summary;

import efub.assignment.community.messageRoom.domain.MessageRoom;

import java.time.LocalDateTime;

public record MessageRoomSummary(Long roomId, String latestMessage, LocalDateTime createdAt) {
    public static MessageRoomSummary from(MessageRoom room) {
        return new MessageRoomSummary(
                room.getId(),
                room.getInitialMessage(), // 최신 메시지가 있다면 변경 가능
                room.getCreatedAt()
        );
    }
}

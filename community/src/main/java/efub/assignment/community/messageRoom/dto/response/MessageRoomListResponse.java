package efub.assignment.community.messageRoom.dto.response;

import efub.assignment.community.messageRoom.dto.summary.MessageRoomSummary;
import java.util.List;

public record MessageRoomListResponse(List<MessageRoomSummary> rooms, Long totalRooms) {}

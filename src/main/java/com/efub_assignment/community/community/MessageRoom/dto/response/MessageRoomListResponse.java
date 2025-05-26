package com.efub_assignment.community.community.MessageRoom.dto.response;

import java.time.LocalDateTime;

public record MessageRoomListResponse(Long messageRoomId,
                                      String latestMessage,
                                      LocalDateTime latestMessageCreatedAt) {
}

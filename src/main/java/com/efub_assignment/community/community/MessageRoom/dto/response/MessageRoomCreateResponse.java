package com.efub_assignment.community.community.MessageRoom.dto.response;

import com.efub_assignment.community.community.MessageRoom.domain.MessageRoom;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Builder
@Getter
public class MessageRoomCreateResponse{
    private Long messageRoomId;
    private Long senderId;
    private Long receiverId;
    private String firstMessage;
    private String messageRoomName;
    private LocalDateTime messageRoomTime;
    private Long postId;

    public static MessageRoomCreateResponse from(MessageRoom messageRoom) {
        return MessageRoomCreateResponse.builder()
                .messageRoomId(messageRoom.getId())
                .senderId(messageRoom.getSender().getMemberId())
                .receiverId(messageRoom.getReceiver().getMemberId())
                .firstMessage(messageRoom.getFirstMessage())
                .postId(messageRoom.getPost().getId())
                .messageRoomName(messageRoom.getMessageRoomName())
                .messageRoomTime(messageRoom.getCreatedAt())
                .build();
    }
}

package com.efub_assignment.community.community.Message.dto.response;

import com.efub_assignment.community.community.Message.domain.Message;
import com.efub_assignment.community.community.MessageRoom.dto.response.MessageRoomCreateResponse;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class MessageCreateResponse {
    private Long messageRoomId;
    private Long senderId;
    private String content;
    private LocalDateTime createdAt;

    public static MessageCreateResponse from(Message message){
        return MessageCreateResponse.builder()
                .messageRoomId(message.getMessageRoom().getId())
                .senderId(message.getSender().getMemberId())
                .content(message.getContent())
                .createdAt(message.getCreatedAt())
                .build();
    }
}

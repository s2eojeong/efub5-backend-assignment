package com.efub_assignment.community.community.Message.dto.response;

import com.efub_assignment.community.community.Message.domain.Message;
import lombok.Builder;
import lombok.Getter;

import java.util.*;
import java.util.stream.Collectors;

@Getter
@Builder
public class MessageListResponse {
    private Long messageRoomId;
    private Long otherMemberId; // 상대방 ID
    private List<MessageResponse> messages;

    public static MessageListResponse from(List<Message> messageList, Long messageRoomId, Long otherMemberId, Long viewerId) {
        List<MessageResponse> responses = messageList.stream()
                .map(message -> MessageResponse.from(message, viewerId))
                .collect(Collectors.toList());

        return MessageListResponse.builder()
                .messageRoomId(messageRoomId)
                .otherMemberId(otherMemberId)
                .messages(responses)
                .build();
    }
}

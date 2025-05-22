package com.efub_assignment.community.community.Message.dto.response;

import com.efub_assignment.community.community.Message.domain.Message;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class MessageResponse {
    private String content;
    private LocalDateTime createdAt;
    private boolean sentByMe;

    public static MessageResponse from(Message message, Long viewerId) {
        return MessageResponse.builder()
                .content(message.getContent())
                .createdAt(message.getCreatedAt())
                .sentByMe(message.getSender().getMemberId().equals(viewerId))
                .build();
    }
}

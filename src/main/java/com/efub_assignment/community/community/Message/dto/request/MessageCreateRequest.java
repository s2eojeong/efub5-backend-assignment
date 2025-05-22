package com.efub_assignment.community.community.Message.dto.request;

import com.efub_assignment.community.community.Message.domain.Message;
import com.efub_assignment.community.community.MessageRoom.domain.MessageRoom;
import com.efub_assignment.community.community.member.domain.Member;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record MessageCreateRequest(@NotNull Long messageRoomId,
                                   @NotNull Long senderId,
                                   @NotBlank String content) {

    public Message toEntity(MessageRoom messageRoom, Member sender){
        return Message.builder()
                .messageRoom(messageRoom)
                .sender(sender)
                .content(content)
                .build();
    }
}

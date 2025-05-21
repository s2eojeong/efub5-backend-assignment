package com.efub_assignment.community.community.MessageRoom.dto.request;

import com.efub_assignment.community.community.MessageRoom.domain.MessageRoom;
import com.efub_assignment.community.community.member.domain.Member;
import com.efub_assignment.community.community.post.domain.Post;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record MessageRoomCreateRequest(@NotNull Long senderId,
                                       @NotNull Long receiverId,
                                       @NotBlank String firstMessage, //첫 쪽지 내용
                                       @NotNull Long postId,
                                       @NotBlank String messageRoomName ) {
    public MessageRoom toEntity(Member sender, Member receiver, Post post) {
        return MessageRoom.builder()
                .sender(sender)
                .receiver(receiver)
                .firstMessage(firstMessage)
                .post(post)
                .messageRoomName(messageRoomName)
                .build();
    }
}

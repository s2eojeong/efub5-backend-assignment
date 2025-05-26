package com.efub_assignment.community.community.Message.domain;

import com.efub_assignment.community.community.MessageRoom.domain.MessageRoom;
import com.efub_assignment.community.community.global.entity.BaseTimeEntity;
import com.efub_assignment.community.community.member.domain.Member;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Message extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long messageId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "message_room_id", nullable = false)
    private MessageRoom messageRoom;

    //보낸사람
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "sender_id", nullable = false)
    private Member sender;

    //메시지 내용
    @Column(nullable = false)
    private String content;

    @Builder
    public Message(MessageRoom messageRoom, Member sender, String content){
        this.messageRoom = messageRoom;
        this.sender = sender;
        this.content = content;
    }

}

package com.efub_assignment.community.community.MessageRoom.domain;

import com.efub_assignment.community.community.global.entity.BaseTimeEntity;
import com.efub_assignment.community.community.member.domain.Member;
import com.efub_assignment.community.community.post.domain.Post;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MessageRoom extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //보낸 사람
    @ManyToOne
    @JoinColumn(name = "sender_id", nullable = false)
    private Member sender;

    //받는 사람
    @ManyToOne
    @JoinColumn(name = "receiver_id", nullable = false)
    private Member receiver;

    //쪽지방 이름
    private String messageRoomName;

    //참여 멤버
    @ManyToMany
    @JoinTable(
            name = "message_room_members",
            joinColumns = @JoinColumn(name = "message_room_id"),
            inverseJoinColumns = @JoinColumn(name = "member_id")
    )
    private List<Member> members;

    //첫 쪽지 내용
    private String firstMessage;

    //쪽지가 시작된 게시글
    @ManyToOne
    @JoinColumn(name = "post_id", nullable = false)
    private Post post;

//    @OneToMany(mappedBy = "messageRoom", cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<Message> messages = new ArrayList<>();

    @Builder
    public MessageRoom (Member sender, Member receiver, String messageRoomName, String firstMessage, Post post){
        this.sender = sender;
        this.receiver = receiver;
        this.messageRoomName = messageRoomName;
        this.firstMessage= firstMessage;
        this.post = post;
    }


}

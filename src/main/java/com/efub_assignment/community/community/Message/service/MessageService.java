package com.efub_assignment.community.community.Message.service;

import com.efub_assignment.community.community.Message.domain.Message;
import com.efub_assignment.community.community.Message.dto.request.MessageCreateRequest;
import com.efub_assignment.community.community.Message.dto.response.MessageCreateResponse;
import com.efub_assignment.community.community.Message.dto.response.MessageListResponse;
import com.efub_assignment.community.community.Message.repository.MessageRepository;
import com.efub_assignment.community.community.MessageRoom.domain.MessageRoom;
import com.efub_assignment.community.community.MessageRoom.repository.MessageRoomRepository;
import com.efub_assignment.community.community.member.domain.Member;
import com.efub_assignment.community.community.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.*;

@Service
@RequiredArgsConstructor
public class MessageService {
    private final MessageRepository messageRepository;
    private final MessageRoomRepository messageRoomRepository;
    private final MemberRepository memberRepository;

    //쪽지 생성
    @Transactional
    public MessageCreateResponse createMessage(MessageCreateRequest request){
        MessageRoom messageRoom = findByMessageRoomId(request.messageRoomId());
        Member sender = findByMemberId(request.senderId());

        Message message = request.toEntity(messageRoom, sender);
        Message saveMessage = messageRepository.save(message);

        return MessageCreateResponse.from(saveMessage);
    }

    //쪽지 목록 조회
    @Transactional
    public MessageListResponse getMessageInRoom(Long messageRoomId, Long viewerId){
        MessageRoom messageRoom = findByMessageRoomId(messageRoomId);
        Member viewer = findByMemberId(viewerId);

        //상대방 ID 구하기
        Long otherMemberId = messageRoom.getSender().getMemberId().equals(viewerId)
                ? messageRoom.getReceiver().getMemberId()
                : messageRoom.getSender().getMemberId();

        List<Message> messageList = messageRepository.findByMessageRoomOrderByCreatedAtAsc(messageRoom);

        return MessageListResponse.from(messageList, messageRoomId, otherMemberId, viewerId);
    }


    private MessageRoom findByMessageRoomId(Long messageRoomId){
        return messageRoomRepository.findById(messageRoomId)
                .orElseThrow(()-> new IllegalArgumentException(("쪽지방이 존재하지 않습니다.")));
    }

    private Member findByMemberId(Long memberId){
        return memberRepository.findByMemberId(memberId)
                .orElseThrow(() -> new IllegalArgumentException("보낸 사람이 존재하지 않는 사용자입니다."));
    }
}

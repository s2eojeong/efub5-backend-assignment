package com.efub_assignment.community.community.MessageRoom.service;

import com.efub_assignment.community.community.Message.domain.Message;
import com.efub_assignment.community.community.Message.repository.MessageRepository;
import com.efub_assignment.community.community.MessageRoom.domain.MessageRoom;
import com.efub_assignment.community.community.MessageRoom.dto.request.MessageRoomCreateRequest;
import com.efub_assignment.community.community.MessageRoom.dto.response.MessageRoomCreateResponse;
import com.efub_assignment.community.community.MessageRoom.dto.response.MessageRoomListResponse;
import com.efub_assignment.community.community.MessageRoom.repository.MessageRoomRepository;
import com.efub_assignment.community.community.member.domain.Member;
import com.efub_assignment.community.community.member.repository.MemberRepository;
import com.efub_assignment.community.community.post.domain.Post;
import com.efub_assignment.community.community.post.repository.PostRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MessageRoomService {
    private final MemberRepository memberRepository;
    private final PostRepository postRepository;
    private final MessageRoomRepository messageRoomRepository;
    private final MessageRepository messageRepository;

    //쪽지방 생성
    @Transactional
    public MessageRoomCreateResponse createMessageRoom(MessageRoomCreateRequest request){
        Member sender = findMemberById(request.senderId());
        Member receiver = findMemberById(request.receiverId());
        Post post = findPostById(request.postId());

        MessageRoom newMessageRoom = request.toEntity(sender, receiver, post);
        messageRoomRepository.save(newMessageRoom);

        return MessageRoomCreateResponse.from(newMessageRoom);
    }

    // 쪽지방 존재 여부 확인
    @Transactional
    public Long checkMessageRoomExist(Long senderId, Long receiverId, Long postId){
        MessageRoom messageRoom = messageRoomRepository.findBySender_memberIdAndReceiver_memberIdAndPost_Id(senderId, receiverId, postId)
                .orElseThrow(()-> new EntityNotFoundException(("쪽지방이 존재하지 않습니다.")));
        return messageRoom.getId();
    }

    //쪽지방 목록 조회
    @Transactional
    public List<MessageRoomListResponse> getMessageRoomsByMemberId(Long memberId){
        List<MessageRoom> messageRooms = messageRoomRepository.findAllByMembers_MemberId(memberId);
        return messageRooms.stream()
                .map(messageRoom -> {
            Message lastestMessage = messageRepository.findTopByMessageRoomOrderByCreatedAtDesc(messageRoom)
                    .orElse(null);
            return new MessageRoomListResponse(
                    messageRoom.getId(),
                    lastestMessage != null ? lastestMessage.getContent() : "",
                    lastestMessage != null ? lastestMessage.getCreatedAt() : null);
        }).collect(Collectors.toList());
    }

    //쪽지방 삭제
    @Transactional
    public void deleteMessageRoom (Long messageRoomId){
        MessageRoom messageRoom = messageRoomRepository.findById(messageRoomId)
                .orElseThrow(()-> new IllegalArgumentException("존재하지 않는 쪽지방입니다."));
        messageRoomRepository.delete(messageRoom);
    }
    private Member findMemberById(Long memberId){
        return memberRepository.findById(memberId)
                .orElseThrow(()-> new IllegalArgumentException("멤버가 존재하지 않습니다."));
    }
    private Post findPostById(Long postId) {
        return postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("게시물이 존재하지 않습니다."));
    }
}

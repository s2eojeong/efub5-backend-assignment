package efub.assignment.community.messageRoom.service;

import efub.assignment.community.alarm.Alarm;
import efub.assignment.community.alarm.AlarmRepository;
import efub.assignment.community.global.exception.BlogException;
import efub.assignment.community.global.exception.ExceptionCode;
import efub.assignment.community.member.entity.Member;
import efub.assignment.community.messageRoom.domain.MessageRoom;
import efub.assignment.community.messageRoom.dto.request.CreateMessageRoomRequestDto;
import efub.assignment.community.messageRoom.dto.response.CreateMessageRoomResponseDto;
import efub.assignment.community.messageRoom.dto.response.MessageRoomListResponse;
import efub.assignment.community.messageRoom.dto.summary.MessageRoomSummary;
import efub.assignment.community.messageRoom.repository.MessageRoomRepository;
import efub.assignment.community.post.domain.Post;
import efub.assignment.community.post.dto.summary.PostSummary;
import efub.assignment.community.post.repository.PostRepository;
import jakarta.validation.Valid;
import lombok.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MessageRoomService {

    private final MessageRoomRepository messageRoomRepository;
    private final PostRepository postRepository;
    private final AlarmRepository alarmRepository;


    //쪽지방 생성
    @Transactional
    public CreateMessageRoomResponseDto createMessageRoom(@Valid CreateMessageRoomRequestDto requestDto, Long senderId, Long receiverId) {
        if(messageRoomRepository.existsBySenderIdAndReceiverIdAndPostId(senderId, receiverId, requestDto.getPostId())){
            throw new IllegalArgumentException("이미 존재하는 쪽지방입니다.");
        }
        if (!postRepository.existsById(requestDto.getPostId())){
            throw new BlogException(ExceptionCode.POST_NOT_FOUND);
        }

        MessageRoom room = requestDto.toEntity(senderId, receiverId);
        MessageRoom savedRoom = messageRoomRepository.save(room);

        messageRoomRepository.save(room);

        alarmRepository.save(Alarm.builder()
                .memberId(receiverId) // 쪽지를 받은 사람에게 알림!
                .type("messageRoom")
                .content("새로운 쪽지방이 생겼어요")
                .build());


        return CreateMessageRoomResponseDto.from(savedRoom);
    }

    //쪽지방 방 여부 조회
    @Transactional
    public MessageRoom checkMessageRoom(Long senderId, Long receiverId, Long postId) {
        return messageRoomRepository.findBySenderIdAndReceiverIdAndPostId(senderId, receiverId, postId)
                .orElseThrow(() -> new BlogException(ExceptionCode.MESSAGE_ROOM_NOT_FOUND));
    }

    //쪽지방 방 목록 조회
    @Transactional(readOnly = true)
    public MessageRoomListResponse getMessageRoomsByMember(Long memberId) {
        List<MessageRoomSummary> summaries = messageRoomRepository.findAllBySenderIdOrReceiverId(memberId, memberId)
                .stream().map(MessageRoomSummary::from).toList();

        return new MessageRoomListResponse(summaries, (long)summaries.size());
    }

    // 쪽지방 삭제
    @Transactional
    public void deleteRoom(Long roomId) {
        MessageRoom room = messageRoomRepository.findById(roomId)
                        .orElseThrow(()->new BlogException(ExceptionCode.MESSAGE_ROOM_NOT_FOUND));
        messageRoomRepository.delete(room);
    }




}

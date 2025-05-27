package efub.assignment.community.message.service;

import efub.assignment.community.global.exception.BlogException;
import efub.assignment.community.global.exception.ExceptionCode;
import efub.assignment.community.message.domain.Message;
import efub.assignment.community.message.dto.CreateMessageRequestDto;
import efub.assignment.community.message.dto.CreateMessageResponseDto;
import efub.assignment.community.message.dto.MessageDto;
import efub.assignment.community.message.dto.MessageListResponseDto;
import efub.assignment.community.message.repository.MessageRepository;
import efub.assignment.community.messageRoom.domain.MessageRoom;
import efub.assignment.community.messageRoom.dto.request.CreateMessageRoomRequestDto;
import efub.assignment.community.messageRoom.repository.MessageRoomRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MessageService {

    private final MessageRoomRepository messageRoomRepository;
    private final MessageRepository messageRepository;

    // 쪽지 생성
    @Transactional
    public CreateMessageResponseDto createMessage(@Valid CreateMessageRequestDto requestDto, Long messageRoomId, Long senderId) {
        MessageRoom room = messageRoomRepository.findById(messageRoomId)
                .orElseThrow(() -> new BlogException(ExceptionCode.MESSAGE_ROOM_NOT_FOUND));

        Message message = requestDto.toEntity(room, senderId);
        Message savedMessage = messageRepository.save(message);
        return CreateMessageResponseDto.from(savedMessage);
    }

    // 쪽지 조회
    @Transactional(readOnly = true)
    public MessageListResponseDto getMessages(Long roomId, Long senderId) {
        MessageRoom room = messageRoomRepository.findById(roomId)
                .orElseThrow(() -> new BlogException(ExceptionCode.MESSAGE_ROOM_NOT_FOUND));

        Long receiverId = room.getSenderId().equals(senderId) ? room.getReceiverId() : room.getSenderId();

        List<Message> messageList = messageRepository.findByMessageRoomIdOrderByCreatedAtAsc(roomId);

        List<MessageDto> messages = messageList.stream()
                .map(msg -> MessageDto.builder()
                        .content(msg.getContent())
                        .createdAt(msg.getCreatedAt())
                        .isSent(msg.getSenderId().equals(senderId))
                        .build())
                .toList();

        return new MessageListResponseDto(roomId, receiverId, messages);
    }


}

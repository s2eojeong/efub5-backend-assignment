package efub.assignment.community.message.service;

import efub.assignment.community.member.domain.Member;
import efub.assignment.community.member.service.MemberService;
import efub.assignment.community.message.domain.Message;
import efub.assignment.community.message.dto.request.MessageRequestDTO;
import efub.assignment.community.message.dto.response.MessageListResponseDTO;
import efub.assignment.community.message.dto.response.MessageResponseDTO;
import efub.assignment.community.message.repository.MessageRepository;
import efub.assignment.community.messageRoom.domain.MessageRoom;
import efub.assignment.community.messageRoom.service.MessageRoomService;
import efub.assignment.community.notification.domain.Notification;
import efub.assignment.community.notification.domain.NotificationType;
import efub.assignment.community.notification.repository.NotificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class MessageService {
    private final MemberService memberService;
    private final MessageRoomService messageRoomService;
    private final MessageRepository messageRepository;
    private final NotificationRepository notificationRepository;

    @Transactional
    public MessageResponseDTO createMessage(Long messageRoomId, MessageRequestDTO requestDTO) {
        //1. messageRoom 유효성 확인
        MessageRoom msgRoom = messageRoomService.msgRoomValidation(messageRoomId);
        //2. senderId 유효성 확인
        Member sender = memberService.findMemberByMemberId(requestDTO.getSenderId());
        //3. 해당 messageRoom의 sender 혹은 receiver에 속하는 지 확인
        if(!msgRoom.getSender().equals(sender) && !msgRoom.getReceiver().equals(sender)) {
            throw new IllegalArgumentException("해당 쪽지방에 참여하지 않은 멤버입니다.");
        }
        //4. 쪽지방의 상대방을 찾아 receiver로 설정
        Member receiver = msgRoom.getSender().equals(sender)
                ? msgRoom.getReceiver()
                : msgRoom.getSender();
        //4. 메세지 생성 및 저장
        Message message = Message.create(requestDTO.getText(), msgRoom, sender, receiver);
        messageRepository.save(message);
        //5. 알림 생성 및 저장
        Notification noti = Notification.create(receiver, NotificationType.MESSAGE, "새로운 쪽지가 있습니다", null, null);
        notificationRepository.save(noti);
        //5. entity -> DTO
        return MessageResponseDTO.from(message);
    }

    public MessageListResponseDTO getMessageList(Long messageRoomId, Long memberId) {
        //1. messageRoom 유효성 확인
        MessageRoom msgRoom = messageRoomService.msgRoomValidation(messageRoomId);
        //2. 상대방 찾기
        Member opponent = msgRoom.getSender().getMemberId().equals(memberId) ? msgRoom.getReceiver() : msgRoom.getSender();
        //3. 메세지 목록 조회
        List<Message> msgList = messageRepository.findAllByMessageRoomOrderByCreatedAtAsc(msgRoom);
        //4. DTO 변환
        List<MessageResponseDTO> msgDTOList = msgList.stream()
                .map(msg -> new MessageResponseDTO(
                        msg.getMessageRoom().getMessageRoomId(),
                        msg.getSender().getMemberId(),
                        msg.getReceiver().getMemberId(),
                        msg.getText(),
                        msg.getCreatedAt()
                ))
                .collect(Collectors.toList());
        return new MessageListResponseDTO(
                msgRoom.getMessageRoomId(),
                opponent.getMemberId(),
                msgDTOList
        );
    }
}

package efub.assignment.community.message.service;

import efub.assignment.community.member.domain.Member;
import efub.assignment.community.member.service.MemberService;
import efub.assignment.community.message.domain.Message;
import efub.assignment.community.message.dto.request.MessageRequestDTO;
import efub.assignment.community.message.dto.response.MessageResponseDTO;
import efub.assignment.community.message.repository.MessageRespository;
import efub.assignment.community.messageRoom.domain.MessageRoom;
import efub.assignment.community.messageRoom.repository.MessageRoomRepository;
import efub.assignment.community.messageRoom.service.MessageRoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MessageService {
    private final MemberService memberService;
    private final MessageRoomService messageRoomService;
    private final MessageRespository messageRespository;

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
        messageRespository.save(message);
        //5. entity -> DTO
        return MessageResponseDTO.from(message);
    }

//    public MessageListResponseDTO getMessageList(Long messageRoomId) {
//        //1. messageRoom 유효성 확인
//        //2. 상대방 찾기
//        //3. 메세지 목록 조회
//    }
}

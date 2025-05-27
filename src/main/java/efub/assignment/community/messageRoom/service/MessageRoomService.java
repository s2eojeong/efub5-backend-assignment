package efub.assignment.community.messageRoom.service;

import efub.assignment.community.member.domain.Member;
import efub.assignment.community.member.service.MemberService;
import efub.assignment.community.messageRoom.domain.MessageRoom;
import efub.assignment.community.messageRoom.dto.request.MessageRoomRequestDTO;
import efub.assignment.community.messageRoom.dto.response.MessageRoomResponseDTO;
import efub.assignment.community.messageRoom.repository.MessageRoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class MessageRoomService {
    private final MemberService memberService;
    private final MessageRoomRepository messageRoomRepository;

    public MessageRoomResponseDTO createMessageRoom(MessageRoomRequestDTO requestDTO) {
        //1. receiver, sender 유효성 검사
        Member receiver = memberService.findMemberByMemberId(requestDTO.getReceiverId());
        Member sender = memberService.findMemberByMemberId(requestDTO.getSenderId());
        //2. RequestDTO -> entity
        MessageRoom newMsgRoom = MessageRoom.create(sender, receiver);
        messageRoomRepository.save(newMsgRoom);

        return MessageRoomResponseDTO.from(newMsgRoom);

    }

//    public MessageRoomListResponseDTO getMsgRoomList(Long memberId) {
//        //1. memberId 유효성 검사
//        Member member = memberService.findMemberByMemberId(memberId);
//        //2. 해당 member이 속한 쪽지방 조회
//        List<MessageRoom> messageRooms = messageRoomRepository.findBySenderOrReceiver(member, member);
//        //3. 각 쪽지방의 가장 최근 메세지 조회
//        //4. DTO로 변환
//    }

    public Optional<MessageRoom> findRoomById(Long roomId) {
        return messageRoomRepository.findById(roomId);
    }

    public String deleteMessageRoom(Long roomId, Long memberId) {
        //1. roomId 유효성 검사
        MessageRoom msgRoom = messageRoomRepository.findById(roomId).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 쪽지방입니다."));
        //2. 권한 확인 (sender 혹은 receiver에 속하는가)
        Member member = memberService.findMemberByMemberId(memberId);
        if((member != msgRoom.getReceiver()) && (member != msgRoom.getSender())) {
            throw new IllegalArgumentException("권한이 없는 사용자 입니다.");
        }
        messageRoomRepository.delete(msgRoom);
        return "메세지가 성공적으로 삭제되었습니다.";
    }

    //messageRoom 존재여부
    public MessageRoom msgRoomValidation(Long roomId) {
        MessageRoom msgRoom = messageRoomRepository.findById(roomId)
                .orElseThrow(() -> new IllegalArgumentException("해당 쪽지방이 존재하지 않습니다."));
        return msgRoom;
    }
}

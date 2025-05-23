package efub.assignment.community.messageRoom.controller;

import efub.assignment.community.messageRoom.domain.MessageRoom;
import efub.assignment.community.messageRoom.dto.request.CreateMessageRoomRequestDto;
import efub.assignment.community.messageRoom.dto.response.CreateMessageRoomResponseDto;
import efub.assignment.community.messageRoom.dto.response.MessageRoomListResponse;
import efub.assignment.community.messageRoom.service.MessageRoomService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/message-rooms")
@RequiredArgsConstructor
public class MessageRoomController {
    private final MessageRoomService messageRoomService;

    // 쪽지방 생성
    @PostMapping("/{senderId}/{receiverId}")
    public ResponseEntity<CreateMessageRoomResponseDto> createMessageRoom( @PathVariable("senderId") Long senderId,
                                                                           @PathVariable("receiverId") Long receiverId,
                                                                           @Valid @RequestBody CreateMessageRoomRequestDto requestDto) {
        CreateMessageRoomResponseDto responseDto = messageRoomService.createMessageRoom(requestDto, senderId, receiverId);
        return ResponseEntity.ok(responseDto);
    }

    // 쪽지방 여부 조회
    @GetMapping("/{senderId}/{receiverId}/{postId}")
    public ResponseEntity<Long> CheckMessageRoom(@PathVariable("senderId") Long senderId,
                                                 @PathVariable("receiverId") Long receiverId,
                                                 @PathVariable("postId") Long postId){
        MessageRoom room = messageRoomService.checkMessageRoom(senderId, receiverId, postId);
        return ResponseEntity.ok(room.getId());
    }

    // 특정 멤버에 대한 쪽지방 목록 조회
    @GetMapping("/member/{memberId}")
    public ResponseEntity<MessageRoomListResponse> getMessageRoomsByMember(@PathVariable Long memberId) {
        return ResponseEntity.ok(messageRoomService.getMessageRoomsByMember(memberId));
    }

    // 쪽지방 삭제
    @DeleteMapping("/{roomId}")
    public ResponseEntity<Void> deleteRoom(@PathVariable Long roomId){
        messageRoomService.deleteRoom(roomId);
        return ResponseEntity.noContent().build();
    }



}

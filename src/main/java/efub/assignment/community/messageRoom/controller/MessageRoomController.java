package efub.assignment.community.messageRoom.controller;

import efub.assignment.community.messageRoom.domain.MessageRoom;
import efub.assignment.community.messageRoom.dto.request.MessageRoomRequestDTO;
import efub.assignment.community.messageRoom.dto.response.MessageRoomListResponseDTO;
import efub.assignment.community.messageRoom.dto.response.MessageRoomResponseDTO;
import efub.assignment.community.messageRoom.service.MessageRoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("/message-rooms")
public class MessageRoomController {

    private final MessageRoomService messageRoomService;

    //쪽지방 생성
    @PostMapping("")
    public ResponseEntity<MessageRoomResponseDTO> createMessageRoom(@RequestBody MessageRoomRequestDTO requestDTO) {
        MessageRoomResponseDTO responseDTO = messageRoomService.createMessageRoom(requestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }

    //쪽지방 목록 조회
    @GetMapping("")
    public ResponseEntity<List<MessageRoomListResponseDTO>> getMsgRoomList(@RequestHeader Long memberId) {
        List<MessageRoomListResponseDTO> responseDTO = messageRoomService.getMsgRoomList(memberId);
        return ResponseEntity.status(HttpStatus.OK).body(responseDTO);

    }

    //쪽지방 존재 여부 조회
    @GetMapping("/{roomId}")
    public ResponseEntity<?> getMessageRoomInfo(@PathVariable Long roomId) {
        Optional<MessageRoom> room = messageRoomService.findRoomById(roomId);
        if(room.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(MessageRoomResponseDTO.from(room.get()));
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(Collections.emptyMap());   // 빈 JSON 객체 {}
        }
    }

    //쪽지방 삭제
    @DeleteMapping("/{roomId}")
    public ResponseEntity<String> deleteMessageRoom(@PathVariable Long roomId,
                                                    @RequestHeader Long memberId) {
        String response = messageRoomService.deleteMessageRoom(roomId, memberId);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

}

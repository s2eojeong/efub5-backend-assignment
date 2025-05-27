package efub.assignment.community.message.controller;

import efub.assignment.community.message.dto.request.MessageRequestDTO;
import efub.assignment.community.message.dto.response.MessageResponseDTO;
import efub.assignment.community.message.repository.MessageRespository;
import efub.assignment.community.message.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/message-rooms/{messageRoomId}/messages")
@RequiredArgsConstructor
@RestController
public class MessageController {
    private final MessageService messageService;

    //쪽지 생성
    @PostMapping("")
    public ResponseEntity<MessageResponseDTO> createMessage(@PathVariable Long messageRoomId,
                                                            @RequestBody MessageRequestDTO requestDTO){
        MessageResponseDTO responseDTO = messageService.createMessage(messageRoomId, requestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }

//    //쪽지방의 쪽지 목록 조회
//    @GetMapping("")
//    public ResponseEntity<MessageListResponseDTO> getMessageList(@PathVariable Long messageRoomId) {
//        MessageListResponseDTO responseDTO = messageService.getMessageList(messageRoomId);
//        return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
//    }
}

package efub.assignment.community.message.controller;

import efub.assignment.community.message.dto.CreateMessageRequestDto;
import efub.assignment.community.message.dto.CreateMessageResponseDto;
import efub.assignment.community.message.dto.MessageListResponseDto;
import efub.assignment.community.message.service.MessageService;
import efub.assignment.community.messageRoom.service.MessageRoomService;
import jakarta.validation.Valid;
import lombok.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/messages")
@RequiredArgsConstructor
public class MessageController {

    private final MessageService messageService;

    @PostMapping("/{messageRoomId}/{senderId}")
    public ResponseEntity<CreateMessageResponseDto> sendMessage(@PathVariable("messageRoomId")Long messageRoomId,
                                                                @PathVariable("senderId") Long senderId,
                                                                @RequestBody @Valid CreateMessageRequestDto dto) {
        return ResponseEntity.ok(messageService.createMessage(dto, messageRoomId, senderId));
    }

    @GetMapping("/{senderId}/message-rooms/{roomId}")
    public ResponseEntity<MessageListResponseDto> getMessages(@PathVariable("senderId") Long senderId,
                                                              @PathVariable("roomId") Long messageRoomId) {
        MessageListResponseDto response = messageService.getMessages(messageRoomId, senderId);
        return ResponseEntity.ok(response);
    }


}

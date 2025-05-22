package com.efub_assignment.community.community.Message.controller;

import com.efub_assignment.community.community.Message.dto.request.MessageCreateRequest;
import com.efub_assignment.community.community.Message.dto.response.MessageCreateResponse;
import com.efub_assignment.community.community.Message.dto.response.MessageListResponse;
import com.efub_assignment.community.community.Message.service.MessageService;
import jakarta.validation.Valid;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/messages")
@RequiredArgsConstructor
public class MessageController {

    private final MessageService messageService;

    //쪽지 생성
    @PostMapping
    public ResponseEntity<MessageCreateResponse> createMessage(@RequestBody @Valid MessageCreateRequest request){
        MessageCreateResponse response = messageService.createMessage(request);
        return ResponseEntity.ok(response);
    }

    //쪽지 목록 조회
    @GetMapping
    public ResponseEntity<MessageListResponse> getMessageInRoom(@RequestParam Long messageRoomId,
                                                                @RequestParam Long viewerId){
        MessageListResponse response = messageService.getMessageInRoom(messageRoomId,viewerId);
        return ResponseEntity.ok(response);
    }
}

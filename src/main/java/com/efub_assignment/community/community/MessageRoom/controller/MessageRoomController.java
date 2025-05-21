package com.efub_assignment.community.community.MessageRoom.controller;

import com.efub_assignment.community.community.MessageRoom.dto.request.MessageRoomCreateRequest;
import com.efub_assignment.community.community.MessageRoom.dto.response.MessageRoomCreateResponse;
import com.efub_assignment.community.community.MessageRoom.dto.response.MessageRoomListResponse;
import com.efub_assignment.community.community.MessageRoom.repository.MessageRoomRepository;
import com.efub_assignment.community.community.MessageRoom.service.MessageRoomService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/messageRooms")
@RequiredArgsConstructor
public class MessageRoomController {
    private final MessageRoomService messageRoomService;

    //쪽지방 생성
    @PostMapping
    public ResponseEntity<MessageRoomCreateResponse> createMessageRoom(@RequestBody @Valid MessageRoomCreateRequest request){
        MessageRoomCreateResponse response = messageRoomService.createMessageRoom(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    //쪽지방 존재여부 조회
    @GetMapping("/exists")
    public ResponseEntity<Long> checkMessageRoomExist(@RequestParam Long senderId,
                                                      @RequestParam Long receiverId,
                                                      @RequestParam Long postId){
        Long messageRoomId = messageRoomService.checkMessageRoomExist(senderId, receiverId, postId);
        return ResponseEntity.ok(messageRoomId);
    }

    //쪽지방 목록 조회
    @GetMapping("/lists")
    public ResponseEntity<List<MessageRoomListResponse>> getMessageRooms(@RequestParam Long memberId){
        List<MessageRoomListResponse> response = messageRoomService.getMessageRoomsByMemberId(memberId);
        return ResponseEntity.ok(response);
    }

    //쪽지방 삭제
    @DeleteMapping("/{messageRoomId}")
    public ResponseEntity<String> deleteMessageRoom(@PathVariable("messageRoomId") Long messageRoomId){
        messageRoomService.deleteMessageRoom(messageRoomId);
        return ResponseEntity.ok("쪽지방이 성공적으로 삭제되었습니다.");
    }


}

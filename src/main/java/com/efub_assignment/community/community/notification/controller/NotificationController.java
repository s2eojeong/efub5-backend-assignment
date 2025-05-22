package com.efub_assignment.community.community.notification.controller;

import com.efub_assignment.community.community.notification.dto.response.NotificationResponse;
import com.efub_assignment.community.community.notification.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/notifications")
public class NotificationController {
    private final NotificationService notificationService;

    //조회
    @GetMapping
    public ResponseEntity<List<NotificationResponse>> getNotifications(@RequestParam Long memberId){
        List<NotificationResponse> response = notificationService.getNotifications(memberId);
        return ResponseEntity.ok(response);
    }
}

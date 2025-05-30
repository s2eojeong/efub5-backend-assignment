package efub.assignment.community.notification.controller;

import efub.assignment.community.notification.dto.response.NotificationListResponseDTO;
import efub.assignment.community.notification.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/notification")
public class NotificationController {
    private final NotificationService notificationService;

    
    //알림 목록 조회
    @GetMapping("")
    public ResponseEntity<List<NotificationListResponseDTO>> getNotifications(@RequestHeader Long memberId) {
        List<NotificationListResponseDTO> notifications = notificationService.getNotifications(memberId);
        return ResponseEntity.status(HttpStatus.OK).body(notifications);
    }
}

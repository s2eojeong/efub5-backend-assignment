package com.efub_assignment.community.community.notification.service;

import com.efub_assignment.community.community.member.repository.MemberRepository;
import com.efub_assignment.community.community.notification.domain.Notification;
import com.efub_assignment.community.community.notification.dto.response.NotificationResponse;
import com.efub_assignment.community.community.notification.repository.NotificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.*;

@Service
@RequiredArgsConstructor
public class NotificationService {
    private final NotificationRepository notificationRepository;
    private final MemberRepository memberRepository;

    // 알림 조회
    @Transactional
    public List<NotificationResponse> getNotifications(Long memberId){
        List<Notification> notifications = notificationRepository.findAllByReceiver_MemberIdOrderByCreatedAtDesc(memberId);
        return notifications.stream().map(NotificationResponse::from).toList();
    }
}

package com.efub_assignment.community.community.notification.repository;

import com.efub_assignment.community.community.notification.domain.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.*;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
    List<Notification> findAllByReceiver_MemberIdOrderByCreatedAtDesc(Long receiverId);
}

package efub.assignment.community.notification.service;

import efub.assignment.community.member.domain.Member;
import efub.assignment.community.member.service.MemberService;
import efub.assignment.community.notification.domain.Notification;
import efub.assignment.community.notification.dto.response.NotificationListResponseDTO;
import efub.assignment.community.notification.repository.NotificationRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class NotificationService {
    private final NotificationRepository notificationRepository;
    private final MemberService memberService;

    @Transactional  //이게 있어야 영속성 컨텍스트 안에서 변경된 isRead값이 자동으로 DB에 반영됨(Dirty Checking)
    public List<NotificationListResponseDTO> getNotifications(Long memberId) {
        //1. memberId 유효성 검사
        Member receiver = memberService.findMemberByMemberId(memberId);
        //2. 회원에 해당하는 알림 찾기
        List<Notification> notifications = notificationRepository.findAllByReceiverAndIsReadFalse(receiver);
        //3. 읽음 처리 (isRead = true)
        notifications.forEach(Notification::markAsRead);
        //4. DTO 변환
        return notifications.stream()
                .map(NotificationListResponseDTO::from)
                .toList();
    }
}

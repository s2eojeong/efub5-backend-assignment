package efub.assignment.community.messageRoom.repository;

import efub.assignment.community.messageRoom.domain.MessageRoom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.*;

public interface MessageRoomRepository extends JpaRepository<MessageRoom, Long> {
    // 쪽지방 존재 여부
    boolean existsBySenderIdAndReceiverIdAndPostId(Long senderId, Long receiverId, Long postId);

    // 쪽지방 아이디
    Optional<MessageRoom> findBySenderIdAndReceiverIdAndPostId(Long senderId, Long receiverId, Long postId);

    // 특정 사용자가 참가한 모든 쪽지방
    List<MessageRoom> findAllBySenderIdOrReceiverId(Long senderId, Long receiverId);

}

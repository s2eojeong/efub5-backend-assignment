package efub.assignment.community.messageRoom.repository;

import efub.assignment.community.member.domain.Member;
import efub.assignment.community.messageRoom.domain.MessageRoom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageRoomRepository extends JpaRepository<MessageRoom, Long> {
    List<MessageRoom> findBySenderOrReceiver(Member sender, Member receiver);
}

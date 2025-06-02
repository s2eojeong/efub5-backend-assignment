package efub.assignment.community.message.repository;

import efub.assignment.community.message.domain.Message;
import efub.assignment.community.messageRoom.domain.MessageRoom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MessageRepository extends JpaRepository<Message, Long> {
    //대화 내용 전체 띄워주기
    List<Message> findAllByMessageRoomOrderByCreatedAtAsc(MessageRoom room);

    //Room별 가장 최신 메세지만 리턴
    Optional<Message> findTop1ByMessageRoomOrderByCreatedAtDesc(MessageRoom room);
}

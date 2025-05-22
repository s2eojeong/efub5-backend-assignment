package com.efub_assignment.community.community.Message.repository;

import com.efub_assignment.community.community.Message.domain.Message;
import com.efub_assignment.community.community.MessageRoom.domain.MessageRoom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.*;

public interface MessageRepository extends JpaRepository<Message, Long> {
    //최신 메시지 1개 가져오기
    Optional<Message> findTopByMessageRoomOrderByCreatedAtDesc(MessageRoom messageRoom);

    //정렬
    List<Message> findByMessageRoomOrderByCreatedAtAsc(MessageRoom messageRoom);
}

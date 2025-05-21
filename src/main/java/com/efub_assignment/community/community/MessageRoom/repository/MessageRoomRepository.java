package com.efub_assignment.community.community.MessageRoom.repository;

import com.efub_assignment.community.community.MessageRoom.domain.MessageRoom;
import com.efub_assignment.community.community.member.domain.Member;
import com.efub_assignment.community.community.post.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.swing.text.html.Option;
import java.util.*;

public interface MessageRoomRepository extends JpaRepository<MessageRoom, Long> {
    Optional<MessageRoom> findBySender_memberIdAndReceiver_memberIdAndPost_Id(Long senderId, Long receiverId, Long postId);

    //최신 메세지 찾기
    @Query("SELECT mr FROM MessageRoom mr WHERE mr.sender.memberId = :memberId OR mr.receiver.memberId = :memberId ORDER BY mr.id DESC")
    List<MessageRoom> findAllByMembers_MemberId(@Param("memberId") Long memberId);

}

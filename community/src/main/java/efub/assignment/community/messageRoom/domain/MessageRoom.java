package efub.assignment.community.messageRoom.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@Table(name = "messageRooms")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MessageRoom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "senderId", nullable = false)
    private Long senderId;

    @Column(name = "receiverId", nullable = false)
    private Long receiverId;

    @Column(name = "initialMessage", nullable = false)
    private String initialMessage;

    @Column(name = "postId", nullable = false)
    private Long postId;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Builder
    public MessageRoom(Long senderId, Long receiverId, String initialMessage, Long postId) {
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.initialMessage = initialMessage;
        this.postId = postId;
        this.createdAt = LocalDateTime.now();
    }


}

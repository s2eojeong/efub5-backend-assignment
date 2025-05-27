package efub.assignment.community.message.domain;

import efub.assignment.community.messageRoom.domain.MessageRoom;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Message {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "message_room_id", nullable = false)
    private MessageRoom messageRoom;

    @Column(name = "senderId", nullable = false)
    private Long senderId;

    @Column(name = "content", nullable = false)
    private String content;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Builder
    public Message(MessageRoom messageRoom, Long senderId, String content) {
        this.messageRoom = messageRoom;
        this.senderId = senderId;
        this.content = content;
        this.createdAt = LocalDateTime.now();
    }
}

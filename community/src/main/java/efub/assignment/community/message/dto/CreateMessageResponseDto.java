package efub.assignment.community.message.dto;

import efub.assignment.community.message.domain.Message;
import lombok.*;

import java.time.LocalDateTime;


@Builder
@Getter
@AllArgsConstructor
public class CreateMessageResponseDto {
    private Long messageRoomId;
    private Long senderId;
    private String content;
    private LocalDateTime createdAt;

    public static CreateMessageResponseDto from(Message message) {
        return CreateMessageResponseDto.builder()
                .messageRoomId(message.getMessageRoom().getId())
                .senderId(message.getSenderId())
                .content(message.getContent())
                .createdAt(message.getCreatedAt())
                .build();
    }
}

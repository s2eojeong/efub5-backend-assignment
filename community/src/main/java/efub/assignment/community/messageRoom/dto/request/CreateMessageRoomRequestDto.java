package efub.assignment.community.messageRoom.dto.request;

import efub.assignment.community.messageRoom.domain.MessageRoom;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateMessageRoomRequestDto {

    @NotBlank
    private String initialMessage;

    @NotNull
    private Long postId;

    public MessageRoom toEntity(Long senderId, Long receiverId) {
        return MessageRoom.builder()
                .senderId(senderId)
                .receiverId(receiverId)
                .initialMessage(initialMessage)
                .postId(postId)
                .build();
    }
}

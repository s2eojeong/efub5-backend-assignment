package efub.assignment.community.message.dto;



import efub.assignment.community.message.domain.Message;
import efub.assignment.community.messageRoom.domain.MessageRoom;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateMessageRequestDto{
    @NotBlank
    private String content;

    public Message toEntity(MessageRoom messageRoom, Long senderId) {
        return Message.builder()
                .messageRoom(messageRoom)
                .senderId(senderId)
                .content(content)
                .build();
    }

}

package efub.assignment.community.message.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MessageRequestDTO {
    private Long senderId;
    private String text;
}

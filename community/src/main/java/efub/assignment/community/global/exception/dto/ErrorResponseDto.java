package efub.assignment.community.global.exception.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ErrorResponseDto {
    private String message;
    private int status;
}

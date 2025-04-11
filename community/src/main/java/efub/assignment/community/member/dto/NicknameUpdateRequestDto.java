package efub.assignment.community.member.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class NicknameUpdateRequestDto {

    private String email;

    @NotBlank
    private String nickname;

    private String university;
    private String studentId;
}

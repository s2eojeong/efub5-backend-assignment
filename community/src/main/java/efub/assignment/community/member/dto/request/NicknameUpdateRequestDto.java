package efub.assignment.community.member.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class NicknameUpdateRequestDto {

    @NotBlank(message = "닉네임 공백 안 됨")
    private String nickname;

}

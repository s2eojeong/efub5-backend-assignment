package efub.assignment.community.member.dto;

import efub.assignment.community.member.entity.Member;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

//회원 생성 Request DTO
@Getter
@NoArgsConstructor
public class CreateMemberRequestDto {

    @NotBlank
    private String email;

    @NotBlank
    private String password;

    @NotBlank
    private String nickname;

    @NotBlank
    private String university;

    @NotBlank
    private String studentId;

    // Member 객체로 build
    public Member toEntity() {
        return Member.builder()
                .email(email)
                .password(password)
                .nickname(nickname)
                .university(university)
                .studentId(studentId)
                .build();
    }
}

package efub.assignment.community.member.dto;

import efub.assignment.community.member.entity.Member;
import lombok.Builder;
import lombok.Getter;

@Builder @Getter
public class NicknameUpdateResponseDto {
    //표시: 이메일, 닉네임, 학교, 학번
    private String email;
    private String nickname;
    private String university;
    private String studentId;

    public static NicknameUpdateResponseDto from(Member member) {
        return NicknameUpdateResponseDto.builder()
                .email(member.getEmail())
                .nickname(member.getNickname())
                .university(member.getUniversity())
                .studentId(member.getStudentId())
                .build();
    }
}

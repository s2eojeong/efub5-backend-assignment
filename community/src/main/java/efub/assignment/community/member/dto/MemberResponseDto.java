package efub.assignment.community.member.dto;

import efub.assignment.community.member.entity.Member;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;


//member 조회 후 응답 DTO

@Builder @Getter
public class MemberResponseDto {
    //표시: id, email, 닉네임, 학교, 학번

    private long id;
    private String email;
    private String nickname;
    private String university;
    private String studentId;

    //Member 객체로 build
    public static MemberResponseDto from(Member member) {
        return MemberResponseDto.builder()
                .id(member.getMemberId())
                .email(member.getEmail())
                .nickname(member.getNickname())
                .university(member.getUniversity())
                .studentId(member.getStudentId())
                .build();
    }
}

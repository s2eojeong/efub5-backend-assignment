package efub.assignment.community.board.dto.request;

import efub.assignment.community.board.domain.Board;
import efub.assignment.community.member.entity.Member;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

// Record: 불변한 객체를 정의하는 데이터 클래스
// 모든 필드 final 자동 선언
public record BoardCreateRequest(@NotNull String title,
                                 @Size(min=1, max=30) String explanation,
                                 @NotBlank String notification) {
    public Board toEntity(Member member){
        return Board.builder()
                .title(title)
                .explanation(explanation)
                .notification(notification)
                .owner(member)
                .build();
    }
}
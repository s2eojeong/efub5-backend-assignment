package efub.assignment.community.comment.dto.request;

import jakarta.validation.constraints.Size;

// Record: 불변한 객체를 정의하는 데이터 클래스
// 모든 필드 final 자동 선언
public record CommentUpdateRequest (
        @Size(max = 100, message = "내용은 100자 이하로 입력해야 합니다.")
        String content
){}
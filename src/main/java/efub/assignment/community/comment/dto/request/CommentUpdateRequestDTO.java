package efub.assignment.community.comment.dto.request;

import lombok.Getter;

//확인 다시  해보기
@Getter
public class CommentUpdateRequestDTO {
    //@NotBlank(message="내용은 비어 있을 수 없습니다."); -> controller에 @Valid 붙이면 적용됨
    private String content;
}

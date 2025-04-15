package efub.assignment.community.post.dto.request;

import efub.assignment.community.post.domain.Post;
import efub.assignment.community.member.entity.Member;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

// Record: 불변한 객체를 정의하는 데이터 클래스
// 모든 필드 final 자동 선언
public record PostCreateRequest(@NotNull Long memberId,
                                @NotBlank String title,
                                @Size(min=5, max=500) String content){
    public Post toEntity(Member member){
        return Post.builder()
                .title(title)
                .content(content)
                .writer(member)
                .build();
    }


}


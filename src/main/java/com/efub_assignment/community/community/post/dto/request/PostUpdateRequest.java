package com.efub_assignment.community.community.post.dto.request;

import jakarta.validation.constraints.Size;

public record PostUpdateRequest(
        @Size(min =5, max =500, message = "내용은 500자 이하로 작성해야합니다.") String content
) {
}

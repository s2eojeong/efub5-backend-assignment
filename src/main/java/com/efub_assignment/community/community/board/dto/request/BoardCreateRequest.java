package com.efub_assignment.community.community.board.dto.request;

import com.efub_assignment.community.community.board.domain.Board;
import com.efub_assignment.community.community.member.domain.Member;
import com.efub_assignment.community.community.post.domain.Post;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record BoardCreateRequest(
        @NotNull Long memberId,
        @NotBlank String boardName,
        @NotBlank String description,
        String notice,
        @NotBlank String password
        ) {
    public Board toEntity(Member member){
        return Board.builder()
                .boardName(boardName)
                .description(description)
                .notice(notice)
                .password(password)
                .owner(member)
                .build();
    }
}

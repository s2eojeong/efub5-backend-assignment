package com.efub_assignment.community.community.post.dto.request;

import com.efub_assignment.community.community.board.domain.Board;
import com.efub_assignment.community.community.member.domain.Member;
import com.efub_assignment.community.community.post.domain.Post;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record PostCreateRequest(
        //계정 아이디
        @NotNull Long memberId,
        //게시판 아이디
        @NotNull Long boardId,
        //제목 (빈 내용 안됨)
        @NotBlank String title,
        //내용 (5-500자)
        @Size(min=5, max=500) String content){
    public Post toEntity( Member member, Board board ){
        return Post.builder()
                .title(title)
                .content(content)
                .writer(member)
                .board(board)
                .build();
    }
}


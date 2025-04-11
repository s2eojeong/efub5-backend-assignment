package com.efub_assignment.community.community.board.dto.response;

import com.efub_assignment.community.community.board.domain.Board;

import java.time.LocalDateTime;

public record BoardResponse(Long boardId, Long memberId, String nickname, String boardName, String description, String notice,
                            LocalDateTime createdAt) {
    public static BoardResponse from(Board board){
        return new BoardResponse(
                board.getId(),
                board.getOwner().getMemberId(),
                board.getOwner().getNickname(),
                board.getBoardName(),
                board.getDescription(),
                board.getNotice(),
                board.getCreatedAt()
        );
    }
}

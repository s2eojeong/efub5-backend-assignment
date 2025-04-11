package com.efub_assignment.community.community.board.dto.response;

import java.util.List;

public record BoardListResponse(
        List<BoardResponse> boards,
        long count
) {
}

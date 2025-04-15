package com.efub_assignment.community.community.post.dto.response;

import com.efub_assignment.community.community.post.dto.summary.PostSummary;

import java.util.List;

public record PostListResponse(List<PostSummary> posts, Long totalPosts) {
}

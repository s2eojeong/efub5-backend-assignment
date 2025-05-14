package com.efub_assignment.community.community.global.exception;

public enum ClientExceptionCode {

    // 전체
    INTERNAL_SERVER_ERROR,
    INVALID_PARAMETER,

    // Account
    MEMBER_NOT_FOUND,

    // Post
    POST_NOT_FOUND,
    POST_CONTENT_INVALID_LENGTH,
    POST_MEMBER_MISMATCH
}

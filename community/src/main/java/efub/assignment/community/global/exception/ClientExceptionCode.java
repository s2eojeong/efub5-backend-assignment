package efub.assignment.community.global.exception;

public enum ClientExceptionCode {
    // 전체
    INTERNAL_SERVER_ERROR,
    INVALID_PARAMETER,

    // Member
    MEMBER_NOT_FOUND,

    // Post
    POST_NOT_FOUND,
    POST_CONTENT_INVALID_LENGTH,
    POST_ACCOUNT_MISMATCH,

    // Board
    BOARD_NOT_FOUND,
    BOARD_OWNER_MISMATCH,

    // Comment
    COMMENT_NOT_FOUND,
    COMMENT_ACCOUNT_MISMATCH,

    // Like
    LIKE_NOT_FOUND,
    LIKE_ALREADY_EXISTS
}

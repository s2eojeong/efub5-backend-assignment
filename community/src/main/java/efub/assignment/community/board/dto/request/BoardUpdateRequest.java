package efub.assignment.community.board.dto.request;
import jakarta.validation.constraints.NotNull;

public record BoardUpdateRequest(@NotNull Long ownerId) {}

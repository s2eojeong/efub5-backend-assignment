package efub.assignment.community.alarm;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class AlarmResponseDto {
    private String type;
    private String content;
    private LocalDateTime createdAt;

    public static AlarmResponseDto from(Alarm alarm) {
        return new AlarmResponseDto(
                alarm.getType(),
                alarm.getContent(),
                alarm.getCreatedAt()
        );
    }
}

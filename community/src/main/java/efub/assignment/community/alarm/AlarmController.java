package efub.assignment.community.alarm;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/alarms")
@RequiredArgsConstructor
public class AlarmController {
    private final AlarmService alarmService;

    @GetMapping("/members/{memberId}")
    public ResponseEntity<List<AlarmResponseDto>> getAlarm(@PathVariable("memberId") Long memberId) {
        return ResponseEntity.ok(alarmService.getAlarm(memberId));
    }
}

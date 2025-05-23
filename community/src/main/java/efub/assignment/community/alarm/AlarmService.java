package efub.assignment.community.alarm;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AlarmService {
    private final AlarmRepository alarmRepository;

    @Transactional(readOnly = true)
    public List<AlarmResponseDto> getAlarm(Long memberId){
        return alarmRepository.findAllByMemberIdOrderByCreatedAtDesc(memberId)
                .stream().map(AlarmResponseDto::from).toList();
    }
}

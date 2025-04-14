package efub.assignment.community;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing		// 자동으로 생성일, 수정일 같은 시간 기록해줘! -> 내부적으로 감시자 기능을 켜줘야함 (@CreatedDate, @LastModifiedDate)
public class EfubAssignmentApplication {

	public static void main(String[] args) {
		SpringApplication.run(EfubAssignmentApplication.class, args);
	}

}

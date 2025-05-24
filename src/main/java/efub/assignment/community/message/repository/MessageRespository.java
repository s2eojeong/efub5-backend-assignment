package efub.assignment.community.message.repository;

import efub.assignment.community.message.domain.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRespository extends JpaRepository<Message, Long> {
}

package efub.assignment.community.member.repository;

import efub.assignment.community.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface MembersRepository extends JpaRepository<Member,Long>{
    // 학번 중복 검사를 위한 쿼리
    boolean existsByStudentId(String studentId);

    // Member Id로 조회
    Optional<Member> findByMemberId(Long MemberId);
}

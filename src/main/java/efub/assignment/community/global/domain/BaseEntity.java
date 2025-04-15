package efub.assignment.community.global.domain;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@MappedSuperclass       // 상속받는 엔티티가 필드까지 테이블에 매핑되게 해주는 역할 BaseEntity에서 @Column 설정도 적용가능
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseEntity {
    // 생성일시
    @CreatedDate @Column(updatable = false)
    private LocalDateTime createdAt;

    //수정 일시
    @LastModifiedDate @Column
    private LocalDateTime updatedAt;

    //Getter
    public LocalDateTime getCreatedAt(){
        return createdAt;
    }
    public LocalDateTime getUpdatedAt(){
        return updatedAt;
    }
}

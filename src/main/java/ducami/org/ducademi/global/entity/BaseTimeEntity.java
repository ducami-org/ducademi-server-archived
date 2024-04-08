package ducami.org.ducademi.global.entity;

import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseTimeEntity { // 게시글 entity, 교재 entity 필요
    // 생성일
    @CreatedDate
    private LocalDateTime created;

    // 수정일
    @LastModifiedDate
    private LocalDateTime modified;
}

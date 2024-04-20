package ducami.org.ducademi.domain.lecture.domain;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "lecture")
public class Apply {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

    @Column(nullable = false)
    private Long applierIdx;
    private Long lectureIdx;

    @Builder
    public Apply(Long applierIdx, Long lectureIdx) {
        this.applierIdx = applierIdx;
        this.lectureIdx = lectureIdx;
    }
}
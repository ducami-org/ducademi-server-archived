package ducami.org.ducademi.domain.lecture.domain;


import ducami.org.ducademi.domain.lecture.domain.enums.LectureType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter @NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "lecture")
public class Lecture {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

    @Column(nullable = false)
    private String title;
    private String introduction;
    private String applyStartDate;
    private String applyEndDate;
    private String startDate;
    private String endDate;
    private String target;

    @Enumerated(EnumType.STRING)
    private LectureType category;

    @Column(nullable = false)
    private int maxApplier = 0;

    @Column(unique = true,nullable = false)
    private Long teacherIdx;

    @Builder
    public Lecture(String title,String introduction,String applyStartDate,String applyEndDate,String startDate,String endDate,String target,LectureType category,Long teacherIdx){
        this.title = title;
        this.introduction = introduction;
        this.applyStartDate = applyStartDate;
        this.applyEndDate = applyEndDate;
        this.startDate = startDate;
        this.endDate = endDate;
        this.target = target;
        this.category = category;
        this.teacherIdx = teacherIdx;
    }

}

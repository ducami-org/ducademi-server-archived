package ducami.org.ducademi.domain.lecture.domain;


import ducami.org.ducademi.domain.lecture.domain.enums.LectureType;
import ducami.org.ducademi.global.entity.BaseTimeEntity;
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

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter @NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "lecture")
public class Lecture extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

    @Column(nullable = false)
    private String title;
    private String description;
    private LocalDateTime applyStartDate;
    private LocalDateTime applyEndDate;
    private LocalDate startDate;
    private LocalDate endDate;
    private String target;

    @Enumerated(EnumType.STRING)
    private LectureType category;

    @Column(nullable = false)
    private int maxApplier = 0;

    @Column(unique = true,nullable = false)
    private Long teacherIdx;

    @Builder
    public Lecture(String title,String introduction,LocalDateTime applyStartDate,LocalDateTime applyEndDate,LocalDate startDate,LocalDate endDate,String target,LectureType category,Long teacherIdx, Integer maxApplier){
        this.title = title;
        this.maxApplier = maxApplier;
        this.description = introduction;
        this.applyStartDate = applyStartDate;
        this.applyEndDate = applyEndDate;
        this.startDate = startDate;
        this.endDate = endDate;
        this.target = target;
        this.category = category;
        this.teacherIdx = teacherIdx;
    }

    public void updateLecture(String title,String introduction,int maxApplier,LocalDate startDate,LocalDate endDate,String target,LocalDateTime applyEndDate,LectureType category) {
        this.title = title.isBlank() ? this.title : title;
        this.description = title.isBlank() ? this.description : introduction;
        this.maxApplier = String.valueOf(maxApplier).isBlank() ? this.maxApplier : maxApplier;
        this.startDate = startDate == null ? this.startDate : startDate;
        this.endDate = endDate == null ? this.endDate : endDate;
        this.target = target.isBlank() ? this.target : target;
        this.applyEndDate = applyEndDate == null ? this.applyEndDate : applyEndDate;
        this.category = category.getCategory().isBlank() ? this.category : category;
    }

}

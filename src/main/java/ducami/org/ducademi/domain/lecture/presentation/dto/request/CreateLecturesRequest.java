package ducami.org.ducademi.domain.lecture.presentation.dto.request;


import ducami.org.ducademi.domain.lecture.domain.Lecture;
import ducami.org.ducademi.domain.lecture.domain.enums.LectureType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
public class CreateLecturesRequest {

//    private String thumbNail;
    private String title;
    private String introduction;
    private String maxApplier;
    private LocalDate startDate;
    private LocalDate endDate;
    private LocalDateTime applyStartDate;
    private LocalDateTime applyEndDate;
    private String target;
    private LectureType category;
    private Long teacherIdx;

    public Lecture toEntity(Long idx){
        return Lecture.builder()
                .title(this.title)
                .introduction(this.introduction)
                .maxApplier(Integer.parseInt(this.maxApplier))
                .startDate(this.startDate)
                .endDate(this.endDate)
                .applyStartDate(this.applyStartDate)
                .applyEndDate(this.applyEndDate)
                .target(this.target)
                .teacherIdx(idx).build();
    }


}


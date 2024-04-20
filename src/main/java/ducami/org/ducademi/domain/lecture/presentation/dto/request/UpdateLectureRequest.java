package ducami.org.ducademi.domain.lecture.presentation.dto.request;


import ducami.org.ducademi.domain.lecture.domain.enums.LectureType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;


@Getter
@Builder
@AllArgsConstructor
public class UpdateLectureRequest {

    private String title;
    private String introduction;
    private int maxApplier;
    private LocalDate startDate;
    private LocalDate endDate;
    private String target;
    private LocalDateTime applyEndDate;
    private LectureType category;





}

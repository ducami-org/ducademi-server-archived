package ducami.org.ducademi.domain.lecture.presentation.dto.response;


import ducami.org.ducademi.domain.lecture.domain.Lecture;
import ducami.org.ducademi.domain.member.entity.MemberEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Builder
@AllArgsConstructor
public class LectureInfoResponse {
    private String title;
    private String description;
    private int headCount;
    private int maxApplier;
    private LocalDate startDate;
    private LocalDate endDate;
    private String target;
    private String teacherName;

    public static LectureInfoResponse of(Lecture lecture, MemberEntity member,int headCount) {
        return LectureInfoResponse.builder()
                .title(lecture.getTitle())
                .description(lecture.getDescription())
                .headCount(headCount)
                .maxApplier(lecture.getMaxApplier())
                .startDate(lecture.getStartDate())
                .endDate(lecture.getEndDate())
                .target(lecture.getTarget())
                .teacherName(member.getName())
                .headCount(headCount).build();
    }
}

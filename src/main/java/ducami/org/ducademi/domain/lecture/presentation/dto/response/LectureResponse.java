package ducami.org.ducademi.domain.lecture.presentation.dto.response;

import ducami.org.ducademi.domain.lecture.domain.Lecture;
import ducami.org.ducademi.domain.member.entity.MemberEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;


@Builder
@AllArgsConstructor
@Getter
public class LectureResponse {
    private String title;
    private String teacherName;

    public static LectureResponse of(Lecture lecture, MemberEntity member) {
        return LectureResponse.builder()
                .title(lecture.getTitle())
                .teacherName(member.getName())
                .build();
    }
}

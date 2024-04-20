package ducami.org.ducademi.domain.lecture.presentation.dto.response;


import ducami.org.ducademi.domain.lecture.domain.Lecture;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder @AllArgsConstructor
public class GetManagingResponse {

    private String title;
    private int headCount;

    public static GetManagingResponse of(Lecture lecture, int headCount){
        return GetManagingResponse.builder()
                .title(lecture.getTitle())
                .headCount(headCount).build();
    }
}

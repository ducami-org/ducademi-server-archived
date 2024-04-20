package ducami.org.ducademi.domain.lecture.presentation.dto.request;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class DeleteLectureRequest {
    private String password;
}

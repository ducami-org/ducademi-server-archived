package ducami.org.ducademi.domain.lecture.presentation;

import ducami.org.ducademi.domain.lecture.presentation.dto.response.LectureInfoResponse;
import ducami.org.ducademi.domain.lecture.service.LectureService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class LectureController {

    private final LectureService lectureService;

    /**
     * 강의 정보 조회
     * */
    @GetMapping("lectures/{idx}")
    @ResponseStatus(HttpStatus.OK)
    public LectureInfoResponse getLectureInfo(
            @PathVariable Long idx
    ) {
        return lectureService.getLectureInto(idx);
    }


}

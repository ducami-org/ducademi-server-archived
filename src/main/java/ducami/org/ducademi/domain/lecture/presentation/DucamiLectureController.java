package ducami.org.ducademi.domain.lecture.presentation;


import ducami.org.ducademi.domain.lecture.presentation.dto.request.CreateLecturesRequest;
import ducami.org.ducademi.domain.lecture.service.DucamiLectureService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "ducami")
@RequiredArgsConstructor
public class DucamiLectureController {

    public final DucamiLectureService ducamiLectureService;

    /**
     * 강의 생성
     * */
    @PostMapping("users/{idx}/lectures")
    @ResponseStatus(HttpStatus.CREATED)
    public void createLecture(
            @PathVariable Long idx,
            @RequestBody CreateLecturesRequest request
    ) {
        ducamiLectureService.createLecture(idx,request);
    }

    /**
     * 강의 수정
     * */
    @PutMapping("lectures/{idx}")
    @ResponseStatus(HttpStatus.CREATED)
    public void updateLecture(
            @PathVariable Long idx,
            @RequestBody UpdateLectureRequest request
    ) {
        ducamiLectureService.updateLecture(request);
    }

    /**
     * 강의 삭제
     * */
    @DeleteMapping("lectures/{idx}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteLecture(
            @PathVariable Long idx,
            @RequestBody DeleteLectureRequest request
    ) {
        ducamiLectureService.deleteLecture(request);
    }

    /**
     * 내 강의실 & 내가 관리하는 강의
     * */
    @GetMapping("users/{idx}/lectures")
    @ResponseStatus(HttpStatus.OK)
    public void getManagingLectures(
            @RequestParam int page,
            @RequestParam int size,
            @PathVariable Long idx
    ) {
        return ducamiLectureService.getManagingLectures(page,size,idx);
    }


}

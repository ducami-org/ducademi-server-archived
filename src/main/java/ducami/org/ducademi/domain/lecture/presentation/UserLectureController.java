package ducami.org.ducademi.domain.lecture.presentation;


import ducami.org.ducademi.domain.lecture.domain.enums.LectureType;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "user")
@RequiredArgsConstructor
public class UserLectureController {

    private final UserLectureService userLectureService;

    /**
     * 강의 전체 조회
     * */
    @GetMapping("lectures")
    @ResponseStatus(HttpStatus.OK)
    public LectureListResponse getAllLectures(
            @RequestParam int page,
            @RequestParam int size
    ) {
        return userLectureService.getAllLectures(page,size);
    }

    /**
     * 카테고리 분류
     * */
    @GetMapping("lectures")
    @ResponseStatus(HttpStatus.OK)
    public LectureListResponse getLecturesByType(
            @RequestBody List<LectureType> category,
            @RequestParam int page,
            @RequestParam int size
    ) {
        return userLectureService.getLecturesByType(page,size,category);
    }

    /**
     * 키워드 검색 & 카테고리
     * */
    @GetMapping("lectures/{keyword}")
    @ResponseStatus(HttpStatus.OK)
    public LectureListResponse getLecturesByKeyword(
            @RequestParam int page,
            @RequestParam int size,
            @PathVariable String keyword,
            @RequestBody List<LectureType> category

    ) {
        userLectureService.getLecturesByKeyword(page,size,keyword,category);
    }

    /**
     * 내가 수강한 강의 = 내 강의실
     * */
    @GetMapping("/users/{idx}/lectures")
    @ResponseStatus(HttpStatus.OK)
    public AppliedLectureListResponse getAppliedLecture(
            @RequestParam int page,
            @RequestParam int size,
            @PathVariable Long idx
    ) {
        userLectureService.getAppliedLecture(page,size,idx);
    }


}

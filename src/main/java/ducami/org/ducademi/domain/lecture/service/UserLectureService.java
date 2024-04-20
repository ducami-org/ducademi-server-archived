package ducami.org.ducademi.domain.lecture.service;


import ducami.org.ducademi.domain.lecture.domain.Apply;
import ducami.org.ducademi.domain.lecture.domain.Lecture;
import ducami.org.ducademi.domain.lecture.domain.enums.LectureType;
import ducami.org.ducademi.domain.lecture.domain.repository.ApplyRepository;
import ducami.org.ducademi.domain.lecture.domain.repository.LectureRepository;
import ducami.org.ducademi.domain.lecture.presentation.dto.response.LectureListResponse;
import ducami.org.ducademi.domain.lecture.presentation.dto.response.LectureResponse;
import ducami.org.ducademi.domain.member.entity.MemberEntity;
import ducami.org.ducademi.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserLectureService {

    private final LectureRepository lectureRepository;
    private final MemberRepository memberRepository;
    private final ApplyRepository applyRepository;

    public LectureListResponse getAllLectures(int page, int size){
        Pageable pageable = PageRequest.of(page, size, Sort.Direction.DESC, "created");
        Page<Lecture> lectureList = lectureRepository.findAll(pageable);

        return toLectureListResponse(lectureList);
    }

    public LectureListResponse getLecturesByCategory(int page, int size,LectureType category){
        Pageable pageable = PageRequest.of(page, size, Sort.Direction.DESC, "created");
        Page<Lecture> lectureList = lectureRepository.findAllByCategory(pageable,category);

        return toLectureListResponse(lectureList);
    }

    public LectureListResponse getLecturesByKeyword(int page,int size,String keyword){
        Pageable pageable = PageRequest.of(page, size, Sort.Direction.DESC, "created");
        Page<Lecture> lectureList = lectureRepository.findAllByKeyword(pageable,keyword);

        return toLectureListResponse(lectureList);
    }

    public LectureListResponse getAppliedLecture(int page,int size,Long applierIdx){
        Pageable pageable = PageRequest.of(page, size, Sort.Direction.DESC, "created");
        Page<Apply> applyList = applyRepository.findAllByApplierIdx(pageable,applierIdx);

        List<LectureResponse> list = applyList.stream()
                .map(item -> {
                    Lecture lecture = lectureRepository.findByIdx(item.getLectureIdx());
                    MemberEntity member = memberRepository.findById(lecture.getTeacherIdx())
                            .orElseThrow(() -> new IllegalArgumentException("User not found"));
                    return LectureResponse.of(lecture,member);
                })
                .toList();

        return LectureListResponse.builder()
                .list(list)
                .build();
    }

    private LectureListResponse toLectureListResponse(Page<Lecture> lectureList) {
        List<LectureResponse> list = lectureList.stream()
                .map(item -> {
                    MemberEntity member = memberRepository.findById(item.getTeacherIdx())
                            .orElseThrow(() -> new IllegalArgumentException("User not found"));
                    return LectureResponse.of(item,member);
                })
                .toList();

        return LectureListResponse.builder()
                .list(list)
                .build();
    }




}

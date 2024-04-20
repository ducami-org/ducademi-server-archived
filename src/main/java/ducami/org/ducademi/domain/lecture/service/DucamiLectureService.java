package ducami.org.ducademi.domain.lecture.service;


import ducami.org.ducademi.domain.lecture.domain.Apply;
import ducami.org.ducademi.domain.lecture.domain.Lecture;
import ducami.org.ducademi.domain.lecture.domain.repository.ApplyRepository;
import ducami.org.ducademi.domain.lecture.domain.repository.LectureRepository;
import ducami.org.ducademi.domain.lecture.presentation.dto.request.CreateLecturesRequest;
import ducami.org.ducademi.domain.lecture.presentation.dto.request.DeleteLectureRequest;
import ducami.org.ducademi.domain.lecture.presentation.dto.request.UpdateLectureRequest;
import ducami.org.ducademi.domain.lecture.presentation.dto.response.ApplierResponse;
import ducami.org.ducademi.domain.lecture.presentation.dto.response.GetManagingListResponse;
import ducami.org.ducademi.domain.lecture.presentation.dto.response.GetManagingResponse;
import ducami.org.ducademi.domain.member.entity.MemberEntity;
import ducami.org.ducademi.domain.member.repository.MemberRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Sort;

import java.util.List;


@Service
@RequiredArgsConstructor
public class DucamiLectureService {

    private final MemberRepository memberRepository;
    private final LectureRepository lectureRepository;
    private final ApplyRepository applyRepository;
    private final PasswordEncoder encoder;

    @Transactional
    public void createLecture(Long teacherIdx, CreateLecturesRequest request){
        memberRepository.findById(teacherIdx)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        lectureRepository.save(request.toEntity(teacherIdx));
    }

    @Transactional
    public void updateLecture(Long lectureIdx, UpdateLectureRequest request){
        Lecture lecture = lectureRepository.findById(lectureIdx)
                .orElseThrow(() -> new IllegalArgumentException("Lecture not found"));

        lecture.updateLecture(request.getTitle(),request.getIntroduction(),request.getMaxApplier(),request.getStartDate(),request.getEndDate(),request.getTarget(),request.getApplyEndDate(),request.getCategory());
    }

    @Transactional
    public void deleteLecture(Long lectureIdx, DeleteLectureRequest request){
        Lecture lecture = lectureRepository.findById(lectureIdx)
                .orElseThrow(() -> new IllegalArgumentException("Lecture not found"));

        MemberEntity member = memberRepository.findById(lecture.getTeacherIdx())
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        if (!encoder.matches(request.getPassword(), member.getPassword()))
            throw new IllegalArgumentException("wrong Password");

        lectureRepository.delete(lecture);
    }

    @Transactional
    public GetManagingListResponse getManagingLectures(int page, int size, Long userIdx) {
        Pageable pageable = PageRequest.of(page, size, Sort.Direction.DESC, "modifiedDateTime");
        MemberEntity member = memberRepository.findById(userIdx)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        Page<Lecture> lectureList = lectureRepository.findAllByTeacherIdx(pageable, userIdx);

        List<GetManagingResponse> list = lectureList.stream()
                .map(item -> {
                    int headCount = applyRepository.countByLectureIdx(item.getIdx());
                    return GetManagingResponse.of(item,headCount);
                })
                .toList();

        return GetManagingListResponse.builder()
                .name(member.getName())
                .list(list)
                .build();
    }

    public List<ApplierResponse> getAllAppliers(Long lectureIdx){
        List<Apply> applyList = applyRepository.findAllByLectureIdx(lectureIdx);

        List<MemberEntity> memberList = applyList.stream()
                .map(item -> memberRepository.findByIdx(item.getApplierIdx()))
                .toList();

        return memberList.stream()
                .map(ApplierResponse::of)
                .toList();
    }

}

package ducami.org.ducademi.domain.lecture.service;


import ducami.org.ducademi.domain.lecture.domain.Lecture;
import ducami.org.ducademi.domain.lecture.domain.repository.ApplyRepository;
import ducami.org.ducademi.domain.lecture.domain.repository.LectureRepository;
import ducami.org.ducademi.domain.lecture.presentation.dto.response.LectureInfoResponse;
import ducami.org.ducademi.domain.member.entity.MemberEntity;
import ducami.org.ducademi.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class LectureService {

    private final LectureRepository lectureRepository;
    private final MemberRepository memberRepository;
    private final ApplyRepository applyRepository;

    public LectureInfoResponse getLectureInto(Long lectureIdx){
        Lecture lecture = lectureRepository.findById(lectureIdx)
                .orElseThrow(() -> new IllegalArgumentException("lecture not found"));

        MemberEntity member = memberRepository.findByIdx(lecture.getIdx());
        int headCount = applyRepository.countByLectureIdx(lectureIdx);

        return LectureInfoResponse.of(lecture,member,headCount);
    }
}

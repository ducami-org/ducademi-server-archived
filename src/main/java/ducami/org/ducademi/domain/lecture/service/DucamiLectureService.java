package ducami.org.ducademi.domain.lecture.service;


import ducami.org.ducademi.domain.lecture.domain.repository.LectureRepository;
import ducami.org.ducademi.domain.lecture.presentation.dto.request.CreateLecturesRequest;
import ducami.org.ducademi.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DucamiLectureService {

    private final MemberRepository memberRepository;
    private final LectureRepository lectureRepository;

    public void createLecture(Long idx, CreateLecturesRequest request){
        memberRepository.findById(idx)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        lectureRepository.save(request.toEntity());
    }


}

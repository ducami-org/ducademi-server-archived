package ducami.org.ducademi.domain.lecture.domain.repository;

import ducami.org.ducademi.domain.lecture.domain.Lecture;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LectureRepository extends JpaRepository<Lecture,Long> {
}

package ducami.org.ducademi.domain.lecture.domain.repository;

import ducami.org.ducademi.domain.lecture.domain.Lecture;
import ducami.org.ducademi.domain.lecture.domain.enums.LectureType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface LectureRepository extends JpaRepository<Lecture,Long> {

    @Query("SELECT l FROM Lecture l WHERE l.teacherIdx = :teacherIdx")
    Page<Lecture> findAllByTeacherIdx(Pageable pageable,Long teacherIdx);

    @Query("SELECT l FROM Lecture l WHERE l.category = :category")
    Page<Lecture> findAllByCategory(Pageable pageable, LectureType category);

    @Query("SELECT l FROM Lecture l WHERE l.title LIKE %:keyword%")
    Page<Lecture> findAllByKeyword(Pageable pageable, String keyword);

    Lecture findByIdx(long idx);
}

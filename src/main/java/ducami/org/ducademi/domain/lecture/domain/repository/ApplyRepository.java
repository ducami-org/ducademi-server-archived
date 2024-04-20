package ducami.org.ducademi.domain.lecture.domain.repository;


import ducami.org.ducademi.domain.lecture.domain.Apply;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ApplyRepository extends JpaRepository<Apply,Long> {

    int countByLectureIdx(Long LectureIdx);

    @Query("SELECT a FROM Apply a WHERE a.applierIdx = :applierIdx")
    Page<Apply> findAllByApplierIdx(Pageable pageable, Long applierIdx);

    @Query("SELECT a FROM Apply a WHERE a.lectureIdx = :lectureIdx")
    List<Apply> findAllByLectureIdx(Long lectureIdx);
}
